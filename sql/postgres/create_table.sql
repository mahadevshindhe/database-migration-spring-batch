
CREATE TABLE public.department (
	id int8 NOT NULL,
	dept_name varchar NULL,
	CONSTRAINT department_pkey PRIMARY KEY (id)
);

CREATE TABLE public.student (
	id bigserial NOT NULL,
	first_name varchar NULL,
	last_name varchar NULL,
	email varchar NULL,
	dept_id int8 NULL,
	is_active varchar NULL,
	CONSTRAINT student_pkey PRIMARY KEY (id)
);


ALTER TABLE public.student ADD CONSTRAINT dept_id FOREIGN KEY (dept_id) REFERENCES public.department(id);

CREATE TABLE public.subjects_learning (
	id int8 NULL,
	sub_name varchar NULL,
	student_id int8 NULL,
	marks_obtained int8 NULL
);

ALTER TABLE public.subjects_learning ADD CONSTRAINT student_id FOREIGN KEY (student_id) REFERENCES public.student(id);