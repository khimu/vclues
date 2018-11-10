##
# You should look at the following URL's in order to grasp a solid understanding
# of Nginx configuration files in order to fully unleash the power of Nginx.
# http://wiki.nginx.org/Pitfalls
# http://wiki.nginx.org/QuickStart
# http://wiki.nginx.org/Configuration
#
# Generally, you will want to move this file somewhere, and start with a clean
# file but keep this around for reference. Or just disable in sites-enabled.
#
# Please see /usr/share/doc/nginx-doc/examples/ for more detailed examples.

server {
       listen         80;
       server_name    vclues.com;
       return         301 https://$server_name$request_uri;
}


map $http_upgrade $connection_upgrade {
    default   upgrade;
    ''        close;
}

server {
        listen 80;
  	listen 443 ssl;

        root    /root/vclues;

        index index.html;

        server_name vclues.com;

        access_log /var/log/nginx/vclues.com/access.log;
        error_log /var/log/nginx/vclues.com/error.log;

        ssl_certificate           /etc/nginx/cert.crt;
        ssl_certificate_key       /etc/nginx/cert.key;

        ssl on;
        ssl_session_cache  builtin:1000  shared:SSL:10m;
        ssl_protocols  TLSv1 TLSv1.1 TLSv1.2;
        ssl_ciphers HIGH:!aNULL:!eNULL:!EXPORT:!CAMELLIA:!DES:!MD5:!PSK:!RC4;
        ssl_prefer_server_ciphers on;


        # go to tomcat server port 9020
        # if running tomcat enable this
        location / {
                # Use this configuration if running spring boot project. "/opt/shop" is where jar file is
                proxy_pass                          http://vclues.com:8080;

                proxy_http_version 1.1;
                proxy_set_header Upgrade $http_upgrade;
                proxy_set_header Connection $connection_upgrade;
		proxy_set_header Host $http_host;
        	proxy_set_header X-Real-IP $remote_addr;
        	proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;

        	#proxy_redirect      http://vclues.com:8080;
        }
}

server {
        listen 80 ;

        root /var/www/html/info;
        index index.php index.html index.htm;

        server_name info.vclues.com;

        access_log /var/log/nginx/info.vclues.com/access.log;
        error_log /var/log/nginx/info.vclues.com/error.log;

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        location ~ \.php$ {
        	try_files $uri =404;
        	fastcgi_pass unix:/var/run/php5-fpm.sock;
        	fastcgi_index index.php;
        	fastcgi_param SCRIPT_FILENAME $document_root$fastcgi_script_name;
        	include fastcgi_params;
	}
}

server {
        listen 80 ;
        #listen [::]:80;

        root /var/www/html;
        index index.php index.html index.htm;

        server_name www.vclues.com;

	#location / {
        #	root /usr/share/nginx/html;
        #}


        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        location ~ \.php$ {
        	try_files $uri =404;
        	fastcgi_pass unix:/var/run/php5-fpm.sock;
        	fastcgi_index index.php;
        	fastcgi_param SCRIPT_FILENAME $document_root$fastcgi_script_name;
        	include fastcgi_params;
        }

}
