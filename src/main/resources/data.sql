/*
insert into tRole (role_name) values ('ROLE_USER');
insert into tRole (role_name) values ('ROLE_ADMIN');
*/

/*insert into tCategory (category_name) values ('상의');
insert into tCategory (category_name) values ('하의');
insert into tCategory (category_name) values ('셔츠');
insert into tCategory (category_name) values ('수트');
insert into tCategory (category_name) values ('신발');
insert into tCategory (category_name) values ('악세사리');*/
/*
insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content1.gif', '스콘 린넨 라운드 니트', 50000, 20, '스콘 린넨 라운드 니트는 여름에 시원하게 착용할 수 있는 린넨 소재의 스콘 스타일 니트입니다. 편안한 착용감과 세련된 실루엣으로 다양한 스타일링을 즐길 수 있습니다.', 10, date_format(now(), '%Y%m%d%H%i%s'), 1);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content2.gif', '피그먼트 오버핏 미시간 반팔 티셔츠', 50000, 20, '피그먼트 오버핏 미시간 반팔 티셔츠는 미시간 원단과 오버핏 실루엣이 특징인 티셔츠입니다. 멋스러운 피그먼트 염색 처리로 독특한 컬러감을 가지고 있으며, 편안한 착용감과 스타일리시한 룩을 연출할 수 있습니다.', 9, date_format(now(), '%Y%m%d%H%i%s'), 1);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content3.gif', '오버핏 마리랜드 반팔 티셔츠', 50000, 20, '오버핏 마리랜드 반팔 티셔츠는 넉넉한 실루엣과 마리랜드 패턴이 특징인 반팔 티셔츠입니다. 편안하면서도 스타일리시한 룩을 완성할 수 있으며, 다양한 하의와 매치하여 다양한 스타일을 즐길 수 있습니다.', 8, date_format(now(), '%Y%m%d%H%i%s'), 1);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content4.gif', '피그먼트 와이드 면 팬츠', 50000, 20, '피그먼트 와이드 면 팬츠는 넓은 다리부터 테이퍼드된 실루엣이 특징인 면 소재의 팬츠입니다. 피그먼트 염색 처리로 독특한 컬러감을 가지고 있으며, 편안한 착용감과 스타일리시한 룩을 연출할 수 있습니다.', 7, date_format(now(), '%Y%m%d%H%i%s'), 2);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content5.gif', '라미프 벨트 와이드 슬랙스', 50000, 20, '라미프 벨트 와이드 슬랙스는 넓은 다리와 벨트 디테일이 특징인 슬랙스입니다. 라미프 소재의 고급스러운 질감과 벨트를 활용한 포인트로 세련된 룩을 완성할 수 있습니다. 다양한 상황에 어울리는 다재다능한 아이템입니다.', 6, date_format(now(), '%Y%m%d%H%i%s'), 2);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content6.gif', '칠린 썸머 와이드팬츠', 50000, 20, '칠린 썸머 와이드팬츠는 여름에 시원하고 경쾌한 착용감을 제공하는 와이드팬츠입니다. 가벼운 소재와 넓은 다리로 통기성을 높여서 더위를 식히며, 여유로운 실루엣으로 편안한 착용감을 선사합니다. 다양한 상의와 매치하여 시원한 여름 스타일을 완성할 수 있습니다.', 5, date_format(now(), '%Y%m%d%H%i%s'), 2);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content7.gif', '프리미엄 오버핏 린넨 셔츠', 50000, 20, '프리미엄 오버핏 린넨 셔츠는 고급스러운 린넨 소재로 제작된 오버핏 셔츠입니다. 편안하면서도 세련된 실루엣을 연출할 수 있으며, 시원한 착용감과 독특한 질감이 매력적인 아이템입니다.', 4, date_format(now(), '%Y%m%d%H%i%s'), 3);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content1.gif', '1+1 디츠 오버핏 코튼 셔츠', 50000, 20, '1+1 디츠 오버핏 코튼 셔츠는 코튼 소재로 제작된 오버핏 셔츠로, 1+1 이벤트로 두 개의 셔츠를 함께 구매할 수 있는 상품입니다. 편안한 착용감과 다양한 스타일링을 즐길 수 있는 아이템입니다.', 3, date_format(now(), '%Y%m%d%H%i%s'), 3);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content2.gif', '르노 텍스쳐 린넨 셔츠', 50000, 20, '르노 텍스쳐 린넨 셔츠는 텍스쳐가 돋보이는 린넨 소재로 제작된 셔츠입니다. 세련된 디자인과 독특한 질감으로 스타일리시한 룩을 완성할 수 있으며, 여름 시즌에 시원하고 시크한 분위기를 연출할 수 있습니다.', 2, date_format(now(), '%Y%m%d%H%i%s'), 3);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content1.gif', '프론 투버튼 수트 세트', 50000, 20, '프론 투버튼 수트 세트는 고품질의 소재와 세련된 디자인으로 구성된 수트 세트입니다. 투버튼 재킷과 일치하는 팬츠가 함께 제공되어 세련된 정장룩을 연출할 수 있으며, 프로페셔널한 분위기를 완성할 수 있습니다.', 1, date_format(now(), '%Y%m%d%H%i%s'), 4);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content2.gif', '스탠 쓰리버튼 수트 셋업', 50000, 20, '스탠 쓰리버튼 수트 셋업은 쓰리버튼 재킷과 일치하는 팬츠로 구성된 수트 세트입니다. 심플하면서도 멋스러운 디자인으로 스타일리시한 룩을 연출할 수 있으며, 다양한 자리에서 활용할 수 있는 아이템입니다.', 0, date_format(now(), '%Y%m%d%H%i%s'), 4);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content3.gif', '라일 투버튼 와이드 세트', 50000, 20, '라일 투버튼 와이드 세트는 투버튼 재킷과 와이드한 실루엣의 팬츠로 구성된 세트입니다. 트렌디하면서도 여성스러운 분위기를 연출할 수 있으며, 다양한 스타일에 맞게 조합하여 유니크한 룩을 완성할 수 있습니다.', 0, date_format(now(), '%Y%m%d%H%i%s'), 4);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content4.gif', '로니 벨크로 로퍼', 50000, 20, '로니 벨크로 로퍼는 편안한 착용감과 스포티한 디자인이 특징인 로퍼입니다. 벨크로 스트랩 디테일로 신발을 간편하게 착용할 수 있으며, 다양한 캐주얼 스타일에 잘 어울리는 아이템입니다.', 0, date_format(now(), '%Y%m%d%H%i%s'), 5);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content5.gif', '로핀 데일리 슬리퍼', 50000, 20, '로핀 데일리 슬리퍼는 편안한 착용감과 심플한 디자인이 특징인 슬리퍼입니다. 일상적인 활동에 적합하며, 부드러운 소재와 편안한 실루엣으로 피로를 풀고 휴식을 취할 수 있는 아이템입니다.', 0, date_format(now(), '%Y%m%d%H%i%s'), 5);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content6.gif', '오르 데일리 슬리퍼', 50000, 20, '오르 데일리 슬리퍼는 스타일리시한 디자인과 편안한 착용감을 가진 슬리퍼입니다. 멋스러운 디자인과 다양한 컬러로 다양한 캐주얼 룩에 잘 어울리며, 편안하면서도 세련된 스타일을 완성할 수 있는 아이템입니다.', 0, date_format(now(), '%Y%m%d%H%i%s'), 5);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content7.gif', '맥스 레더벨트', 50000, 20, '맥스 레더벨트는 고급스러운 레더 소재로 제작된 벨트입니다. 심플하면서도 세련된 디자인으로 다양한 패션 아이템과 잘 어울리며, 허리를 감싸 안정감 있게 착용할 수 있는 아이템입니다.', 0, date_format(now(), '%Y%m%d%H%i%s'), 6);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content1.gif', '타미 레더벨트', 50000, 20, '타미 레더벨트는 타미 로고가 돋보이는 레더 소재의 벨트입니다. 스포티하면서도 시크한 분위기를 연출할 수 있으며, 다양한 캐주얼 스타일에 잘 어울리는 아이템입니다.', 0, date_format(now(), '%Y%m%d%H%i%s'), 6);

insert into tProduct (image_url, product_name, price, stock, description, order_count, register_date, category_code) values
    ('http://localhost:3000/image/content2.gif', '베이직 타이', 50000, 20, '베이직 타이는 심플하면서도 클래식한 디자인의 넥타이입니다. 다양한 색상과 패턴으로 다양한 스타일에 어울리며, 정장이나 비즈니스 캐주얼 룩에 멋을 더해줄 수 있는 아이템입니다.', 0, date_format(now(), '%Y%m%d%H%i%s'), 6);
*/