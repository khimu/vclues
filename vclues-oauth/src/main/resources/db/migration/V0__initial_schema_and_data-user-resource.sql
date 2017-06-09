CREATE TABLE IF NOT EXISTS `authority` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `credentials` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `enabled` tinyint(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `credentials_authority` (
  `credentials_id` bigint(20) NOT NULL,
  `authorities_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO authority VALUES(1,'ROLE_OAUTH_ADMIN');
INSERT INTO authority VALUES(2,'ROLE_ADMIN');
INSERT INTO authority VALUES(3,'ROLE_USER');

INSERT INTO credentials VALUES(1,1,'oauth_admin','admin',0);
INSERT INTO credentials VALUES(2,1,'resource_admin','admin',0);
INSERT INTO credentials VALUES(3,1,'user','user',0);

INSERT INTO oauth2.credentials_authority (credentials_id, authorities_id) VALUE (1, 1);
