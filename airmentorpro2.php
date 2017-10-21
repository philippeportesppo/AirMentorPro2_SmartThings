<html>
   <head>
      <title>Air Mentor Pro 2 for SmartThings</title>
   </head>

   <body>
        <h2>Air Mentor Pro 2 instant measures</h2>
        <table border="0" cellspacing="0" cellpadding="4">
        <tr>
                <td> CO2</td>
                <td> PM2.5</td>
                <td> PM10</td>
                <td> Temperature_C</td>
                <td> Temperature_Calibrated_C</td>
                <td> Humdity</td>
                <td> TVOC</td>
                <td> IAQ</td>
                <td> Battery</td>
                <td> UGW_FeelsLike_C</td>
                <td> UGW_DewPoint_C</td>
                <td> UGW_Humidity</td>
                <td> UGW_Temperature_C</td>
                <td> UGW_Weather</td>
                <td> UGW_Icon_Nt</td>
                <td> UGW_Weather</td>
        </tr>
        <tr>
<?php
session_id('AirMentorPro');
session_start();

if (isset($_GET["Action"])) {
        if ($_GET["Action"]=="set") {

                if (isset($_GET["CO2"])) {
                        $_SESSION["CO2"]= $_GET["CO2"];}
                else {
                        if (!isset($_SESSION["CO2"]))
                            $_SESSION["CO2"]="na";
                }

                if (isset($_GET["PM25"])) {
                        $_SESSION["PM25"]=$_GET["PM25"];}
                else {
                        if (!isset($_SESSION["PM25"]))
                            $_SESSION["PM25"]="na";
                }

                if (isset($_GET["PM10"])) {
                        $_SESSION["PM10"]=$_GET["PM10"];}
                else {
                        if (!isset($_SESSION["PM10"]))
                            $_SESSION["PM10"]="na";
                }

                if (isset($_GET["TEM_ACT"])) {
                        $_SESSION["TEM_ACT"]=$_GET["TEM_ACT"];}
                else {
                        if (!isset($_SESSION["TEM_ACT"]))
                            $_SESSION["TEM_ACT"]="na";
                }

                if (isset($_GET["TEM_CAL"])) {
                        $_SESSION["TEM_CAL"]=$_GET["TEM_CAL"];}
                else {
                        if (!isset($_SESSION["TEM_CAL"]))
                            $_SESSION["TEM_CAL"]="na";
                }

                if (isset($_GET["HUM"])) {
                        $_SESSION["HUM"]=$_GET["HUM"];}
                else {
                        if (!isset($_SESSION["HUM"]))
                            $_SESSION["HUM"]="na";
                }

                if (isset($_GET["TVOC"])) {
                        $_SESSION["TVOC"]=$_GET["TVOC"];}
                else {
                        if (!isset($_SESSION["TVOC"]))
                            $_SESSION["TVOC"]="na";
                }

                if (isset($_GET["IAQ"]))  {
                        $_SESSION["IAQ"]=$_GET["IAQ"];}
                else {
                        if (!isset($_SESSION["IAQ"]))
                            $_SESSION["IAQ"]=="na";
                }

                if (isset($_GET["BATT"]))  {
                        $_SESSION["BATT"]=$_GET["BATT"];}
                else {
                        if (!isset($_SESSION["BATT"]))
                            $_SESSION["BATT"]="na";
                }

                if (isset($_GET["UGW_FL"])) {
                        $_SESSION["UGW_FL"]=$_GET["UGW_FL"];}
                else {
                        if (!isset($_SESSION["UGW_FL"]))
                            $_SESSION["UGW_FL"]="na";
                }

                if (isset($_GET["UGW_DP"])) {
                        $_SESSION["UGW_DP"]=$_GET["UGW_DP"];}
                else {
                        if (!isset($_SESSION["UGW_DP"]))
                            $_SESSION["UGW_DP"]="na";
                }

                if (isset($_GET["UGW_HUM"])) {
                        $_SESSION["UGW_HUM"]=$_GET["UGW_HUM"];}
                else {
                        if (!isset($_SESSION["UGW_HUM"]))
                            $_SESSION["UGW_HUM"]="na";
                }

                if (isset($_GET["UGW_TEM"])) {
                        $_SESSION["UGW_TEM"]=$_GET["UGW_TEM"];}
                else {
                        if (!isset($_SESSION["UGW_TEM"]))
                            $_SESSION["UGW_TEM"]="na";
                }

                if (isset($_GET["UGW_ICON"])) {
                        $_SESSION["UGW_ICON"]=$_GET["UGW_ICON"];}
                else {
                        if (!isset($_SESSION["UGW_ICON"]))
                            $_SESSION["UGW_ICON"]="na";
                }

                if (isset($_GET["UGW_NT"])) {
                        $_SESSION["UGW_NT"]=$_GET["UGW_NT"];}
                else {
                        if (!isset($_SESSION["UGW_NT"]))
                            $_SESSION["UGW_NT"]="na";
                }

                if (isset($_GET["UGW_WEATHER"])) {
                        $_SESSION["UGW_WEATHER"]=$_GET["UGW_WEATHER"];}
                else {
                        if (!isset($_SESSION["UGW_WEATHER"]))
                            $_SESSION["UGW_WEATHER"]="na";
                }
        }
        else
        {

                echo "<td>" . $_SESSION['CO2'] . "</td>";
                echo "<td>" . $_SESSION['PM25'] . "</td>";
                echo "<td>" . $_SESSION['PM10'] . "</td>";
                echo "<td>" . $_SESSION['TEM_ACT'] . "</td>";
                echo "<td>" . $_SESSION['TEM_CAL'] . "</td>";
                echo "<td>" . $_SESSION['HUM'] . "</td>";
                echo "<td>" . $_SESSION['TVOC'] . "</td>";
                echo "<td>" . $_SESSION['IAQ'] . "</td>";
                echo "<td>" . $_SESSION['BATT'] . "</td>";
                echo "<td>" . $_SESSION['UGW_FL'] . "</td>";
                echo "<td>" . $_SESSION['UGW_DP'] . "</td>";
                echo "<td>" . $_SESSION['UGW_HUM'] . "</td>";
                echo "<td>" . $_SESSION['UGW_TEM'] . "</td>";
                echo "<td>" . $_SESSION['UGW_ICON'] . "</td>";
                echo "<td>" . $_SESSION['UGW_NT'] . "</td>";
                echo "<td>" . $_SESSION['UGW_WEATHER'] . "</td>";
        }
}
?>
   </tr>
   </table>
   </body>
</html>