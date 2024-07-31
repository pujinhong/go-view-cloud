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