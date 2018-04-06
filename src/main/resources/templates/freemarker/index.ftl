<!DOCTYPE html>

<html>

<head>
    <title>Thymeleaf Sandbox</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>

<h2>Spring WebFlux - Thymeleaf Sandbox application</h2>

<p>Using template engine: <strong>FreeMarker</strong> (see logo below)</p>

<img src="/images/freemarker_logo.png">

<p><span>Current time is ${.now}</span></p>

<p>
    Some URLs:
</p>

<ul>
    <li>Thymeleaf:
        <ul>
            <li><a href="/thymeleaf">Index</a></li>
            <li><a href="/smalllist.thymeleaf">Small List</a></li>
            <li><a href="/biglist-full.thymeleaf">Big List in FULL mode</a></li>
            <li><a href="/biglist-chunked.thymeleaf">Big List in CHUNKED mode</a></li>
            <li><a href="/biglist-datadriven.thymeleaf">Big List in DATA-DRIVEN mode</a></li>
        </ul>
    </li>
    <li>FreeMarker:
        <ul>
            <li><a href="/freemarker">Index</a></li>
            <li><a href="/smalllist.freemarker">Small List</a></li>
            <li><a href="/biglist.freemarker">Big List</a></li>
        </ul>
    </li>
    <li>JSON:
        <ul>
            <li><a href="/smalllist.json">Small List</a></li>
            <li><a href="/biglist.json">Big List</a></li>
        </ul>
    </li>
</ul>

</body>

</html>