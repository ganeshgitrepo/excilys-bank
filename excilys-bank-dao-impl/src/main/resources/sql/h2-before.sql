CREATE TABLE ref_account_category
(
	id character varying(20) NOT NULL,
	name character varying(20),
	CONSTRAINT ref_account_category_pkey PRIMARY KEY (id)
);

CREATE TABLE ref_account_type
(
	id character varying(20) NOT NULL,
	name character varying(20),
	category character varying(20) NOT NULL,
	CONSTRAINT ref_account_type_pkey PRIMARY KEY (id),
	CONSTRAINT fk8b112a78a6a6a9a5 FOREIGN KEY (category) REFERENCES ref_account_category (id)
);

CREATE TABLE ref_card_type
(
	id character varying(20) NOT NULL,
	name character varying(20),
	CONSTRAINT ref_card_type_pkey PRIMARY KEY (id)
);

CREATE TABLE ref_operation_status
(
	id character varying(20) NOT NULL,
	name character varying(20),
	CONSTRAINT ref_operation_status_pkey PRIMARY KEY (id)
);

CREATE TABLE ref_operation_type
(
	id character varying(20) NOT NULL,
	name character varying(20),
	CONSTRAINT ref_operation_type_pkey PRIMARY KEY (id)
);

CREATE TABLE usr
(
	id integer NOT NULL,
	email character varying(30) NOT NULL,
	firstname character varying(30) NOT NULL,
	lastname character varying(30) NOT NULL,
	login character varying(30) NOT NULL,
	password character varying(30) NOT NULL,
	CONSTRAINT usr_pkey PRIMARY KEY (id),
	CONSTRAINT usr_login_key UNIQUE (login)
);

CREATE TABLE ref_role
(
	id character varying(20) NOT NULL,
	name character varying(20),
	CONSTRAINT ref_role_pkey PRIMARY KEY (id)
);

CREATE TABLE usr_role
(
	usr_id integer NOT NULL,
	role_id character varying(20) NOT NULL,
	CONSTRAINT fk35b7b06132b73cc2 FOREIGN KEY (role_id) REFERENCES ref_role (id),
	CONSTRAINT fk35b7b061572a0195 FOREIGN KEY (usr_id) REFERENCES usr (id)
);

CREATE TABLE account
(
	id integer NOT NULL,
	balance numeric(19,2) NOT NULL,
	balance_date timestamp,
	number character varying(20) NOT NULL,
	type character varying(20) NOT NULL,
	CONSTRAINT account_pkey PRIMARY KEY (id),
	CONSTRAINT fke49f160df1b37925 FOREIGN KEY (type) REFERENCES ref_account_type (id),
	CONSTRAINT account_number_key UNIQUE (number)
);

CREATE TABLE usr_account
(
	usr_id integer NOT NULL,
	account_id integer NOT NULL,
	CONSTRAINT fk92d95242500081f6 FOREIGN KEY (account_id) REFERENCES account (id),
	CONSTRAINT fk92d95242572a0195 FOREIGN KEY (usr_id) REFERENCES usr (id)
);

CREATE TABLE card
(
	id integer NOT NULL,
	number character varying(20) NOT NULL,
	pending numeric(19,2),
	pending_date timestamp,
	account integer NOT NULL,
	type character varying(20) NOT NULL,
	CONSTRAINT card_pkey PRIMARY KEY (id),
	CONSTRAINT fk1f731039b5efb6 FOREIGN KEY (account) REFERENCES account (id),
	CONSTRAINT fk1f7310c412a384 FOREIGN KEY (type) REFERENCES ref_card_type (id),
	CONSTRAINT card_number_key UNIQUE (number)
);

CREATE TABLE operation
(
	id integer NOT NULL,
	amount numeric(19,2) NOT NULL,
	date timestamp NOT NULL,
	name character varying(20) NOT NULL,
	account integer NOT NULL,
	card integer,
	status character varying(20) NOT NULL,
	type character varying(20) NOT NULL,
	CONSTRAINT operation_pkey PRIMARY KEY (id),
	CONSTRAINT fk932e54739b5efb6 FOREIGN KEY (account) REFERENCES account (id),
	CONSTRAINT fk932e5477891d3eb FOREIGN KEY (status) REFERENCES ref_operation_status (id),
	CONSTRAINT fk932e547891f3844 FOREIGN KEY (card) REFERENCES card (id),
	CONSTRAINT fk932e547e6fafceb FOREIGN KEY (type) REFERENCES ref_operation_type (id)
);