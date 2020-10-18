CREATE TABLE if not exists public.dezenas_mega_sena_ordenadas (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	primeira int4 NOT NULL,
	quarta int4 NOT NULL,
	quinta int4 NOT NULL,
	segunda int4 NOT NULL,
	sexta int4 NOT NULL,
	terceira int4 NOT NULL,
	CONSTRAINT dezenas_mega_sena_ordenadas_pkey PRIMARY KEY (id)
);