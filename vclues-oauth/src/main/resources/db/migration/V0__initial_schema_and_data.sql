CREATE TABLE `oauth_client_details` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(256) DEFAULT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `client_id` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `oauth_access_token` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `token_id` varchar(256) DEFAULT NULL,
  `token` longblob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` longblob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token_id` (`token_id`),
  UNIQUE KEY `authentication_id` (`authentication_id`),
  KEY `auth_id_idx` (`authentication_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `oauth_client_token` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `token_id` varchar(256) DEFAULT NULL,
  `token` longblob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `authentication_id` (`authentication_id`),
  KEY `auth_id_idx` (`authentication_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `oauth_refresh_token` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `token_id` varchar(256) DEFAULT NULL,
  `token` longblob,
  `authentication` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` longblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `oauth_approvals` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `todo` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  `done` tinyint(1) DEFAULT NULL,
  `done_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `message` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO oauth_client_details
  (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES
  ('read-only-client', 'partner-services', null, 'read', 'implicit', 'http://localhost:8080', NULL, 7200, 0, NULL, 'false');

-- this is needed for partner-api access using the shell script to get a token
INSERT INTO oauth_client_details
  (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES
  ('curl-client', 'partner-services', 'client-secret', 'read,write', 'client_credentials', '', 'ROLE_ADMIN', 7200, 0, NULL, 'false');

  INSERT INTO oauth_client_details
  (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES
  ('7CH9aSS1ADs7', 'partner-services', 'UUjsQx68GnVx', 'read,write', 'client_credentials', '', 'ROLE_ADMIN', 7200, 0, NULL, 'false');
  
