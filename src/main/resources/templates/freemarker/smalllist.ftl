<!DOCTYPE html>

<html>

<head>
    <title>Thymeleaf Sandbox</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>

<h2>Small Listing (8,715 entries)</h2>

<p>Using template engine: <strong>FreeMarker</strong></p>

<table>
    <thead>
    <tr>
        <th>Playlist ID</th>
        <th>Playlist Name</th>
        <th>Track Name</th>
        <th>Artist Name</th>
        <th>Album Title</th>
    </tr>
    </thead>
    <tbody>
        <#list entries as e>
        <tr>
            <td>${e.id?html}</td>
            <td>${e.title?html}</td>
            <td>${e.content?html}</td>
        </tr>
        </#list>
    </tbody>
</table>

</body>

</html>