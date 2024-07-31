
-- 块表


CREATE TABLE public.t_sys_file (
	id varchar NOT NULL,
	file_name varchar NULL,
	file_size int4 NULL,
	file_suffix varchar NULL,
	create_time varchar NULL,
	md5 varchar NULL,
	virtual_key varchar NULL,
	relative_path varchar NULL,
	absolute_path varchar NULL,
	CONSTRAINT t_sys_file_pkey PRIMARY KEY (id)
);

CREATE TABLE public.t_sys_user (
	id varchar NOT NULL,
	username varchar NULL,
	"password" varchar NULL,
	nickname varchar NULL,
	dep_id int4 NULL,
	pos_id varchar NULL,
	CONSTRAINT t_sys_user_pkey PRIMARY KEY (id)
);

CREATE TABLE public.t_goview_project (
	id varchar NOT NULL,
	project_name varchar NULL,
	state int2 NULL,
	create_time varchar NULL,
	create_user_id varchar NULL,
	is_delete int2 NULL,
	index_image varchar NULL,
	remarks varchar NULL,
	CONSTRAINT t_goview_project_pkey PRIMARY KEY (id)
);

CREATE TABLE public.t_goview_project_data (
	id varchar NOT NULL,
	project_id varchar NULL,
	create_time varchar NULL,
	create_user_id varchar NULL,
	"content" text NULL,
	CONSTRAINT t_goview_project_data_pkey PRIMARY KEY (id)
);

CREATE TABLE t_vw_bento_sql (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	name TEXT,
	title TEXT,
	db_code TEXT,
	"sql" TEXT,
	create_by TEXT,
	create_time TEXT,
	update_by TEXT,
	update_time TEXT,
	CONSTRAINT t_vw_bento_sql_pk PRIMARY KEY (id)
);

CREATE TABLE t_vw_datasource (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	code TEXT,
	name TEXT,
	remark TEXT,
	db_type TEXT,
	db_driver TEXT,
	db_url TEXT,
	db_instance TEXT,
	db_username TEXT,
	db_password TEXT,
	db_port INTEGER,
	db_host TEXT,
	del_flag INTEGER DEFAULT (0),
	online INTEGER DEFAULT (1),
	create_time TEXT,
	create_by TEXT,
	update_time TEXT,
	update_by TEXT
);
CREATE UNIQUE INDEX t_vw_datasource_id_IDX ON t_vw_datasource (id);