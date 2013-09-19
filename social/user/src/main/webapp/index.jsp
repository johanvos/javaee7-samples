JavaEE7 social index
<p>
    Create a user:
<form action="rest/user/create" method="POST">
    <table>
        <tr>
            <td>name:</td><td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>email:</td><td><input type="email" name="email"/></td>
        </tr>
        <tr>
            <td>password:</td><td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td><input type="submit"/></td><td></td>
        </tr>
    </table>
</form>
</p>
<p>
    Or login:
    <form action="rest/user/validate" method="POST">
    <table>
        <tr>
            <td>email</td><td><input type="text" name="email"/></td>
        </tr>

        <tr>
            <td>password:</td><td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td><input type="submit"/></td><td></td>
        </tr>
    </table>
</form>
</p>