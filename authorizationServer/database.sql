CREATE DATABASE "authorizationServer";

CREATE TABLE auth_user(
                          id INT,
                          username varchar(45),
                          password varchar(45),
                          authority varchar(45)
);

CREATE TABLE client(
                        id INT,
                        client_id varchar(45),
                        secret varchar(45),
                        scope varchar(45),
                        auth_method varchar(45),
                        grant_type varchar(45),
                        redirect_uri varchar(200)
);

INSERT INTO auth_user (id, username, password, authority)
VALUES (1, 'bill', '12345', 'read');

INSERT INTO client (id, client_id, secret, scope, auth_method, grant_type, redirect_uri)
VALUES (1, 'client', 'secret', 'openid', 'client_secret_basic', 'authorization_code', 'https://springone/authorized');