# Adds random padding to response to mitigate BREACH:


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


http://wiki.nginx.org/HttpLimitReqModule
