INSERT INTO shop
    (id, name)
VALUES
    (1, '가게 1'),
    (2, '가게 2'),
    (3, '가게 3'),
    (4, '가게 4'),
    (5, '가게 5');

INSERT INTO item_category
    (id, name, shop_id)
VALUES
    (1, 'A 카테고리', 1),
    (2, 'B 카테고리', 1),
    (3, 'C 카테고리', 1);

INSERT INTO item
    (id, name, price)
VALUES
    (1, '음식 1', 1000),
    (2, '음식 2', 2000),
    (3, '음식 3', 3000),
    (4, '음식 4', 4000),
    (5, '음식 5', 5000);

INSERT INTO item_category_detail
    (id, item_category_id, item_id)
VALUES
    (1, 1, 1),
    (2, 1, 2),
    (3, 1, 3),
    (4, 1, 4),
    (5, 1, 5);
