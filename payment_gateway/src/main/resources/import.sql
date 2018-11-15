insert into payment_type(code, name) values ('1', 'CreditCard');
insert into payment_type(code, name) values ('2', 'PayPal');
insert into payment_type(code, name) values ('3', 'Bitcoin');

insert into magazine(issn, title, membership, merchant_id, merchant_password) values ('12345678', 'Chemistry, Physics and Mathematics Review', 1000, 'aaXXbb2134', '1478rfy3ehwndf8fefs');
insert into magazine(issn, title, membership, merchant_id, merchant_password) values ('87654321', 'Biology, History and Geography Journal', 500, 'bbYYcc6578', '214er6dtgyuwbjqscaknvji');
insert into magazine(issn, title, membership, merchant_id, merchant_password) values ('74651xaa', 'On Literature And Philosophy', null, 'ccZZdd09ab', '45gweasdddaghrbfdsvg');

insert into magazine_types(magazine, type) values ('aaXXbb2134', 1);
insert into magazine_types(magazine, type) values ('aaXXbb2134', 2);
insert into magazine_types(magazine, type) values ('aaXXbb2134', 3);
insert into magazine_types(magazine, type) values ('bbYYcc6578', 1);
insert into magazine_types(magazine, type) values ('bbYYcc6578', 2);
insert into magazine_types(magazine, type) values ('bbYYcc6578', 3);
insert into magazine_types(magazine, type) values ('ccZZdd09ab', 1);
insert into magazine_types(magazine, type) values ('ccZZdd09ab', 2);
insert into magazine_types(magazine, type) values ('ccZZdd09ab', 3);