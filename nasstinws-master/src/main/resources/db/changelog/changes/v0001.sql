
create table if not exists users
(
    id SERIAL PRIMARY KEY,
    first_name   varchar(100) not null,
    last_name    varchar(100) not null,
    middle_name  varchar(100),
    email       varchar(45)  not null,
    phone       varchar(15),
    password    varchar(150) not null,
    enabled     boolean default true,
    created_by   integer      not null,
    created_at   timestamp    not null,
    modified_by  integer,
    modified_at  timestamp
    );

create table if not exists role
(
    id SERIAL PRIMARY KEY,
    name varchar(45) not null
    );

create table if not exists users_role
(
    user_id integer not null references users (id) on update cascade on delete cascade,
    role_id integer not null references role (id) on update cascade on delete cascade
    );


CREATE TABLE IF NOT EXISTS access_token
(
    token         text NOT NULL primary key,
    refresh_token text NOT NULL,
    created_at    timestamp without time zone       NOT NULL,
    expiry_date   timestamp without time zone       NOT NULL
);

CREATE TABLE IF NOT EXISTS refresh_token
(
    token       text  not null primary key,
    created_at  timestamp without time zone       NOT NULL,
    expiry_date timestamp without time zone       NOT NULL
);


CREATE TABLE audit_trail (
                             id SERIAL PRIMARY KEY,
                             profile character varying(150) NOT NULL,
                             continent character varying(100) NOT NULL,
                             city character varying NOT NULL,
                             country character varying(100) NOT NULL,
                             device character varying(150) NOT NULL,
                             ip_address character varying(50) NOT NULL,
                             action character varying(200) NOT NULL,
                             created_at timestamp without time zone NOT NULL
);

CREATE TABLE last_fetch_individual (
                                       id SERIAL PRIMARY KEY,
                                       fetch_date date
);

CREATE TABLE last_fetch_non_individual (
                                           id SERIAL PRIMARY KEY,
                                           fetch_date date
);

CREATE TABLE individual (
                            id SERIAL PRIMARY KEY,
                            bvn character varying(12),
                            jtb_tin character varying(50),
                            first_name character varying(50),
                            last_name character varying(50),
                            middle_name character varying(50),
                            date_of_birth date,
                            gender character varying(20),
                            title character varying(40),
                            tax_payer_photo text,
                            email character varying(100),
                            phone character varying(20),
                            phone_two character varying(20),
                            marital_status character varying(20),
                            state_of_origin character varying(30),
                            house_no character varying(100),
                            street character varying(200),
                            city character varying(100),
                            lga character varying(100),
                            state_of_residence character varying(100),
                            occupation character varying(60),
                            nationality character varying(25),
                            date_of_registration date,
                            tax_authority_code character varying(100),
                            tax_authority_name character varying(100),
                            tax_payer_status character varying(50),
                            created_by   integer      not null,
                            created_at   timestamp    not null,
                            modified_by  integer,
                            modified_at  timestamp
);

CREATE TABLE non_individual (
                                id SERIAL PRIMARY KEY,
                                registration_name character varying(200),
                                main_trade_name character varying(200),
                                type_of_organisation character varying(150),
                                current_tin character varying(20),
                                previous_tin character varying(30),
                                insurance_place character varying(150),
                                date_of_registration date,
                                date_of_incorporation date,
                                line_of_business character varying(150),
                                stat_of_origin character varying(20),
                                sector character varying(20),
                                phone character varying(20),
                                phone_two character varying(20),
                                door_no character varying(100),
                                house_no character varying(100),
                                street character varying(200),
                                city character varying(150),
                                lga character varying(100),
                                ward character varying(100),
                                state character varying(20),
                                date_of_commencement date,
                                director_name character varying(150),
                                director_phone character varying(20),
                                director_email character varying(100),
                                trade_office character varying(100),
                                tax_authority character varying(150),
                                tax_authority_code character varying(100),
                                tax_authority_name character varying(100),
                                tax_payer_status character varying(50),
                                email_address character varying(100),
                                country character varying(50),
                                fin_year_begin character varying(10),
                                fin_year_end character varying(10),
                                registration_number character varying(100),
                                created_by   integer,
                                created_at   timestamp,
                                modified_by  integer,
                                modified_at  timestamp
);


CREATE TABLE tax_detail_non_individual (
                                           id SERIAL PRIMARY KEY,
                                           jtb_tin character varying(15),
                                           old_tin character varying(15),
                                           tax_number character varying(20),
                                           tax_period character varying(50),
                                           assessable_profit character varying(50),
                                           total_profit character varying(50),
                                           tax_payable character varying(50),
                                           tax_paid character varying(50),
                                           payment_date date,
                                           tax_type character varying(50),
                                           tax_authority character varying(150),
                                           tax_office character varying(150),
                                           non_individual_id integer REFERENCES non_individual (id),
                                           created_by   integer      not null,
                                           created_at   timestamp    not null,
                                           modified_by  integer,
                                           modified_at  timestamp
);

CREATE TABLE asset_detail_non_individual (
                                             id SERIAL PRIMARY KEY,
                                             tin character varying(50),
                                             location character varying(100),
                                             asset_type character varying(150),
                                             asset_value character varying(50),
                                             date_aquired date,
                                             description character varying(255),
                                             non_individual_id integer REFERENCES non_individual (id),
                                             created_by   integer      not null,
                                             created_at   timestamp    not null,
                                             modified_by  integer,
                                             modified_at  timestamp
);

CREATE TABLE tax_detail_individual (
                                       id SERIAL PRIMARY KEY,
                                       jtb_tin character varying(15),
                                       old_tin character varying(15),
                                       tax_number character varying(20),
                                       tax_period character varying(50),
                                       assessable_profit character varying(50),
                                       total_profit character varying(50),
                                       tax_payable character varying(50),
                                       tax_paid character varying(50),
                                       payment_date date,
                                       tax_type character varying(50),
                                       tax_authority character varying(150),
                                       tax_office character varying(150),
                                       individual_id integer REFERENCES individual (id),
                                       created_by   integer      not null,
                                       created_at   timestamp    not null,
                                       modified_by  integer,
                                       modified_at  timestamp
);

CREATE TABLE asset_detail_individual (
                                         id SERIAL PRIMARY KEY,
                                         tin character varying(50),
                                         location character varying(100),
                                         asset_type character varying(150),
                                         asset_value character varying(50),
                                         date_aquired date,
                                         description character varying(255),
                                         individual_id integer REFERENCES individual (id),
                                         created_by   integer      not null,
                                         created_at   timestamp    not null,
                                         modified_by  integer,
                                         modified_at  timestamp
);