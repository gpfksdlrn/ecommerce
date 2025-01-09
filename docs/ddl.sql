create table ECOMMERCE.coupons
(
    coupon_id      bigint auto_increment comment '쿠폰 일련번호' primary key,
    coupon_name    varchar(200)                         not null comment '쿠폰 이름',
    discount_type  varchar(50)                          not null comment '할인 타입(AMOUNT or RATE 등)',
    discount_value int                                  not null comment '할인 값(정액 or 정율)',
    coupon_stock   int        default 0                 not null comment '발급 가능(남은) 수량',
    start_date     datetime                             not null comment '쿠폰 사용 가능 시작일',
    end_date       datetime                             not null comment '쿠폰 사용 가능 종료일',
    created_at     datetime   default CURRENT_TIMESTAMP not null comment '생성일시',
    updated_at     datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '수정일시',
    del_yn         tinyint(1) default 0                 not null comment '삭제 여부 (0: N, 1: Y)'
)
    comment '쿠폰 기본정보 테이블 입니다.';

create table ECOMMERCE.items
(
    items_id      bigint auto_increment comment '상품 일련번호' primary key,
    item_name     varchar(200)                         not null comment '상품명',
    item_price    int                                  not null comment '상품가격',
    item_quantity int        default 0                 not null comment '상품 재고 수량',
    created_at    datetime   default CURRENT_TIMESTAMP not null comment '생성일시',
    updated_at    datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '수정일시',
    del_yn        tinyint(1) default 0                 not null comment '삭제 여부 (0: N, 1: Y)'
)
    comment '상품 테이블 입니다.';

create table ECOMMERCE.users
(
    user_id    bigint auto_increment comment '유저 일련번호' primary key,
    user_mail  varchar(255)                         not null,
    user_name  varchar(255)                         not null,
    created_at datetime   default CURRENT_TIMESTAMP not null comment '생성일시',
    updated_at datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '수정일시',
    del_yn     tinyint(1) default 0                 not null comment '삭제 여부 (0: N, 1: Y)'
)
    comment '유저 테이블 입니다.';

create table ECOMMERCE.cart
(
    cart_id    bigint auto_increment comment '장바구니 일련번호' primary key,
    user_id    bigint                               not null comment '유저 일련번호',
    item_id    bigint                               not null comment '상품 일련번호',
    quantity   int        default 0                 not null comment '장바구니에 담은 상품 수량',
    created_at datetime   default CURRENT_TIMESTAMP not null comment '생성일시',
    updated_at datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '수정일시',
    del_yn     tinyint(1) default 0                 not null comment '삭제 여부 (0: N, 1: Y)',
    constraint user_cart_items_items_id_fk
        foreign key (item_id) references ECOMMERCE.items (items_id),
    constraint user_cart_users_user_id_fk
        foreign key (user_id) references ECOMMERCE.users (user_id)
)
    comment '장바구니 테이블 입니다.';

create table ECOMMERCE.oder
(
    oder_id     bigint auto_increment comment '주문 일련번호' primary key,
    user_id     bigint                               not null comment '유저 일련번호',
    item_id     bigint                               not null comment '상품 일련번호',
    order_price int        default 0                 not null comment '결제 금액',
    quantity    int        default 0                 not null comment '상품 수량',
    created_at  datetime   default CURRENT_TIMESTAMP not null comment '생성일시',
    updated_at  datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '수정일시',
    del_yn      tinyint(1) default 0                 not null comment '삭제 여부 (0: N, 1: Y)',
    constraint user_oder_items_items_id_fk
        foreign key (item_id) references ECOMMERCE.items (items_id),
    constraint user_oder_users_user_id_fk
        foreign key (user_id) references ECOMMERCE.users (user_id)
)
    comment '주문(결제) 테이블 입니다.';

create table ECOMMERCE.point
(
    point_id    bigint auto_increment comment '유저 포인트 일련번호' primary key,
    user_id     bigint                               not null,
    user_amount bigint     default 0                 not null,
    created_at  datetime   default CURRENT_TIMESTAMP not null,
    updated_at  datetime   default CURRENT_TIMESTAMP not null,
    del_yn      tinyint(1) default 0                 not null,
    constraint point_users_user_id_fk
        foreign key (user_id) references ECOMMERCE.users (user_id)
)
    comment '사용자별 보유 포인트 테이블 입니다.';

create table ECOMMERCE.user_coupons
(
    user_coupon_id bigint auto_increment comment '유저 쿠폰 일련번호' primary key,
    user_id        bigint                               not null comment '유저 일련번호',
    coupon_id      bigint                               not null comment '쿠폰 일련번호',
    issued_at      datetime   default CURRENT_TIMESTAMP not null comment '쿠폰 발급 일시',
    used_yn        tinyint(1) default 0                 not null comment '사용 여부 (0: N, 1: Y)',
    used_at        datetime                             null comment '쿠폰 사용 일시',
    created_at     datetime   default CURRENT_TIMESTAMP not null comment '생성일시',
    updated_at     datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '수정일시',
    del_yn         tinyint(1) default 0                 not null comment '삭제 여부 (0: N, 1: Y)',
    constraint user_coupons_coupons_coupon_id_fk
        foreign key (coupon_id) references ECOMMERCE.coupons (coupon_id),
    constraint user_coupons_users_user_id_fk
        foreign key (user_id) references ECOMMERCE.users (user_id)
)
    comment '사용자별 보유 쿠폰 테이블 입니다.';