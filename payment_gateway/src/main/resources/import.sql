insert into magazine(issn, title) values ('1', 'Chemistry, Physics and Mathematics Review');
insert into magazine(issn, title) values ('87654321', 'Biology, History and Geography Journal');
insert into magazine(issn, title) values ('74651xaa', 'On Literature And Philosophy');

insert into payment_service_details(id, merchantid, merchant_password, type) values (1, '1', '123456', 'CREDIT_CARD');

insert into bank(id, merchant_id, bank_url) values (1, '1', 'https://localhost:8081/api/bank/getURLandID');
insert into bank(id, merchant_id, bank_url) values (2, '2', 'https://localhost:8095/api/bank/getURLandID');