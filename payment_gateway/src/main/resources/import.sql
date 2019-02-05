
insert into magazine(issn, title) values ('1', 'Chemistry, Physics and Mathematics Review');

insert into magazine(issn, title) values ('12345679', 'Chemistry, Physics and Mathematics Review');
insert into magazine(issn, title) values ('87654321', 'Biology, History and Geography Journal');
insert into magazine(issn, title) values ('74651xaa', 'On Literature And Philosophy');


insert into details(id, merchantid, merchant_password, type) values (1, '1', '123456', 'CREDIT_CARD');

insert into bank(id, merchant_id, bank_url) values (1, '1', 'https://localhost:8081/api/bank/getURLandID');
insert into bank(id, merchant_id, bank_url) values (2, '2', 'https://localhost:8095/api/bank/getURLandID');

insert into magazine(issn, title) values ('15409589', 'ournal of Web Engineering');
insert into details(id, magazine, merchant, password, type) values (2, '15409589', 'AS7kftlYLLaUYwYyYJMsW3K3piIompuO7yJl_5n7YbCBXTjW0WS17IahnEFoauQTOnc5kdrg1YeHHMQY', 'EHtnw-aUi2UfILnFUhzF0fzUTCe5cWxAT6m2K2LJRiWxNqDHjfklZzLPBx6y_HbnEpOIgaPo1zNQ6Foh', 1);
insert into details(id, magazine, merchant, password, type) values (3, '15409589', 'Q-smRAh_a6nF-NVXJarEt48YyHtNag1iX-__bZwx', 'aaaa', 2);



insert into details(id, magazine, merchant, password, type) values (1, '12345679', 'AS7kftlYLLaUYwYyYJMsW3K3piIompuO7yJl_5n7YbCBXTjW0WS17IahnEFoauQTOnc5kdrg1YeHHMQY', 'EHtnw-aUi2UfILnFUhzF0fzUTCe5cWxAT6m2K2LJRiWxNqDHjfklZzLPBx6y_HbnEpOIgaPo1zNQ6Foh', 1);

insert into transaction(order_id, timestamp, payer_id, magazine, quantity, price, currency, type, buyer_name, buyer_surname, buyer_email, merchandise, executed, id_bitcoin, status) values ('2', '2019-02-23T00:00:05Z', 'pera', '12345679', 1, 1000, 'RSD', 1, 'Jelena', 'Kostic', 'kosticka.jelena@gmail.com', 'abcdefgh', true, 1, 'ok');
insert into transaction(order_id, timestamp, payer_id, magazine, quantity, price, currency, type, buyer_name, buyer_surname, buyer_email, merchandise, executed, id_bitcoin, status) values ('1', '2019-02-22T00:00:05Z', 'jelena', '12345679', 1, 1000, 'RSD', 1, 'Jelena', 'Kostic', 'kosticka.jelena@gmail.com', 'abcdefgh', true, 1, 'ok');
insert into transaction(order_id, timestamp, payer_id, magazine, quantity, price, currency, type, buyer_name, buyer_surname, buyer_email, merchandise, executed, id_bitcoin, status) values ('3', '2019-02-22T00:00:05Z', 'jelena', '12345679', 1, 1000, 'RSD', 1, 'Jelena', 'Kostic', 'milicmilan@gmail.com', 'abcdefgh', true, 1, 'ok');




