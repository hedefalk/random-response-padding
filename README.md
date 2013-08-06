# Adds random padding to response to mitigate BREACH

## Usage

```
<filter>
	<filter-name>NoBreach</filter-name>
	<filter-class>se.hedefalk.RandomPaddingFilter</filter-class>
	<init-param>
		<param-name>max-length</param-name>
		<param-value>20</param-value>
	</init-param>
</filter>

```


Combine with rate-limit. In nginx config, something like:

```
limit_req_zone  $cookie_JSESSIONID  zone=one:10m   rate=1r/s;

server {
...
limit_req   zone=one  burst=5;
}
```

## Alternatives

Just turn off gzip:
http://nginx.org/en/docs/http/ngx_http_gzip_module.html


## Se also
* http://arstechnica.com/security/2013/08/no-easy-way-to-stop-breach-from-plucking-secrets-from-https-pages-feds-say/
* http://security.stackexchange.com/questions/39925/breach-a-new-attack-against-http-what-can-be-done/39953#39953
* http://wiki.nginx.org/HttpLimitReqModule
* http://breachattack.com/
