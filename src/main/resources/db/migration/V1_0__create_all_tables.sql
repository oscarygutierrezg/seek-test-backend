CREATE TABLE candidates (
   ID VARCHAR(36),
   EMAIL VARCHAR(50) NOT NULL UNIQUE,
   NAME VARCHAR(50),
   GENDER VARCHAR(50),
   EXPECTED_SALARY DOUBLE,
   YEARS_EXPERIENCE INTEGER,
   created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CONSTRAINT pk_candidate PRIMARY KEY (ID)
);


CREATE TABLE users (
    id VARCHAR(36) PRIMARY KEY,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_login TIMESTAMP
);

CREATE TABLE roles (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);


CREATE TABLE privilege (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);


CREATE TABLE user_rol (
    user_id VARCHAR(36) NOT NULL,
    role_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);


CREATE TABLE roles_privileges (
    role_id VARCHAR(36) NOT NULL,                       -- Identificador del rol
    privilege_id VARCHAR(36) NOT NULL,                  -- Identificador del privilegio
    PRIMARY KEY (role_id, privilege_id),        -- Clave primaria compuesta
    FOREIGN KEY (role_id) REFERENCES roles(id), -- Relación con la tabla roles
    FOREIGN KEY (privilege_id) REFERENCES privilege(id) -- Relación con la tabla privileges
);