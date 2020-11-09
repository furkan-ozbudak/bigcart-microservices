insert into category(name) values ('electronic')
insert into category(name) values ('kitchen')
insert into category(name) values ('fashion')
insert into category(name) values ('sports')

--sub categories
insert into category(name, parent_category_id) values ('laptops', 1)--5

insert into category(name, parent_category_id) values ('microwaves', 2)--6

insert into category(name, parent_category_id) values ('shoes', 3)--7

insert into category(name, parent_category_id) values ('headphones', 1)--8

insert into category(name, parent_category_id) values ('bikes', 4)--9

insert into category(name, parent_category_id) values ('watches', 3)--10

insert into category(name, parent_category_id) values ('sunglasses', 3)--11


--products
insert into product_image (id) values(1)
insert into product(description, name, specifications, category_id) values ('Budget new brand HP laptop', 'HP S-15 Laptop', '16GB RAM, core i9, 3.8Ghz', 5)
insert into vendor_product(product_id, vendor_id, image_url, price, quantity, request_date, status, rating, rating_count) values (1, 1, '1.jpg', 600, 5, '2020-10-31', 'pending', 0, 0)

insert into product_image (id) values(2)
insert into product(description, name, specifications, category_id) values ('Newest technology Samsung R-11 oven', 'Samsung R-11 Oven', 'high heating, economic light', 6)
insert into vendor_product(product_id, vendor_id, image_url, price, quantity, request_date, status, rating, rating_count) values (2, 1, '2.jpg', 1150, 30, '2020-10-31', 'pending', 0, 0)



insert into product_image (id) values(3)
insert into product(description, name, specifications, category_id) values ('Nike sport shoes with comfortable design laptop', 'Nike sport shoes', 'ergonomic, elastic', 7)
insert into vendor_product(product_id, vendor_id, image_url, price, quantity, request_date, status, rating, rating_count) values (3, 2, '3.jpg', 270, 100, '2020-10-31', 'approved', 0, 0)


insert into product_image (id) values(4)
insert into product(description, name, specifications, category_id) values ('Stylish headphone best noise canceling you can get', 'Jabra Headphone', 'Bluetooth 5, 40 hrs play time, ', 8)
insert into vendor_product(product_id, vendor_id, image_url, price, quantity, request_date, status, rating, rating_count) values (4, 1, '4.jpg', 170, 100, '2020-10-31', 'pending', 0, 0)


insert into product_image (id) values(8)
insert into product(description, name, specifications, category_id) values ('Stylish headphone best noise canceling you can get, ', 'Jabra Headphone', 'Bluetooth 5, 40 hrs play time, ', 8)
insert into vendor_product(product_id, vendor_id, image_url, price, quantity, request_date, status, rating, rating_count) values (5, 1, '8.jpg', 570, 100, '2020-10-31', 'approved', 0, 0)


insert into product_image (id) values(5)
insert into product(description, name, specifications, category_id) values ('Nice sung glasses from ray ban ', 'Ray ban Sunglasses ', 'Back, Multiple sizes', 11)
insert into vendor_product(product_id, vendor_id, image_url, price, quantity, request_date, status, rating, rating_count) values (6, 3, '5.jpg', 870, 100, '2020-10-31', 'pending', 0, 0)


insert into product_image (id) values(6)
insert into product(description, name, specifications, category_id) values ('Best road bike you can get ', 'road bike X500', 'ergonomic, elastic', 9)
insert into vendor_product(product_id, vendor_id, image_url, price, quantity, request_date, status, rating, rating_count) values (7, 3, '6.jpg', 570, 100, '2020-10-31', 'pending', 0, 0)


insert into product_image (id) values(10)
insert into product(description, name, specifications, category_id) values ('est road bike you can get', 'road bike X600', 'ergonomic, elastic', 9)
insert into vendor_product(product_id, vendor_id, image_url, price, quantity, request_date, status, rating, rating_count) values (8, 2, '10.jpg', 370, 100, '2020-10-31', 'pending', 0, 0)



insert into product_image (id) values(7)
insert into product(description, name, specifications, category_id) values ('Nike sport shoes with comfortable design ', 'Nike sport shoes', 'ergonomic, elastic', 7)
insert into vendor_product(product_id, vendor_id, image_url, price, quantity, request_date, status, rating, rating_count) values (9, 1, '7.jpg', 170, 100, '2020-10-31', 'approved', 0, 0)


insert into product_image (id) values(9)
insert into product(description, name, specifications, category_id) values ('Classic watch with comfortable design ', 'Classic watch', 'ergonomic, elastic', 10)
insert into vendor_product(product_id, vendor_id, image_url, price, quantity, request_date, status, rating, rating_count) values (10, 1, '9.jpg', 700, 100, '2020-10-31', 'approved', 0, 0)




insert into review(review_id, comment, rating, user_id, vendor_product_product_id, vendor_product_vendor_id) values (1, 'Very good looking shoes but not as much comfortable.', 3, 1, 3, 2)
insert into review(review_id, comment, rating, user_id, vendor_product_product_id, vendor_product_vendor_id) values (2, 'Laptop is very slow and it is freezing.', 1, 2, 1, 1)
insert into review(review_id, comment, rating, user_id, vendor_product_product_id, vendor_product_vendor_id) values (3, 'Oven is overheating and not very safe.', 2, 3, 2, 1)










