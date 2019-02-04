insert into magazine(issn, title) values ('12345679', 'Chemistry, Physics and Mathematics Review');
insert into magazine(issn, title) values ('87654321', 'Biology, History and Geography Journal');
insert into magazine(issn, title) values ('74651xaa', 'On Literature And Philosophy');

insert into details(id, magazine, merchant, password, type) values (1, '12345679', 'AS7kftlYLLaUYwYyYJMsW3K3piIompuO7yJl_5n7YbCBXTjW0WS17IahnEFoauQTOnc5kdrg1YeHHMQY', 'EHtnw-aUi2UfILnFUhzF0fzUTCe5cWxAT6m2K2LJRiWxNqDHjfklZzLPBx6y_HbnEpOIgaPo1zNQ6Foh', 1);

insert into transaction(order_id, timestamp, payer_id, magazine, quantity, price, currency, type, buyer_name, buyer_surname, buyer_email, merchandise, executed, id_bitcoin, status) values ('2', '2019-02-23T00:00:05Z', 'pera', '12345679', 1, 1000, 'RSD', 1, 'Jelena', 'Kostic', 'kosticka.jelena@gmail.com', 'abcdefgh', true, 1, 'ok');
insert into transaction(order_id, timestamp, payer_id, magazine, quantity, price, currency, type, buyer_name, buyer_surname, buyer_email, merchandise, executed, id_bitcoin, status) values ('1', '2019-02-22T00:00:05Z', 'jelena', '12345679', 1, 1000, 'RSD', 1, 'Jelena', 'Kostic', 'kosticka.jelena@gmail.com', 'abcdefgh', true, 1, 'ok');