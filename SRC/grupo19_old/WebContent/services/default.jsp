<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Recarga de selectores</title>
    <script type="text/javascript" src="script/prototype-1.6.0.2.js"></script>
    <script type="text/javascript" src="script/reloaders.js"></script>
    <link rel="stylesheet" href="style/default.css" type="text/css"></link>
</head>
<body>
    <div id="layout">
        <fieldset>
            <legend>Ajax select loader</legend>
            <form name="ftest01" action="#">
                <label for="provincias">Provincias</label>
                <select name="provincias" id="provincias" 
                    onchange="reloadPoblaciones(this);">
                        <option value="0">(Seleccione una provincia)</option>
                        <option value="1">Madrid</option>
                        <option value="2">Málaga</option>
                </select>
                <label for="poblaciones">Población</label>
                <select name="poblaciones" id="poblaciones">
                        <option value="">(Seleccione una provincia)</option>
                </select>
            </form>
        </fieldset>
    </div>
</body>
</html>