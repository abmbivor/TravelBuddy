<h2><?php echo $title; ?></h2>

<?php

       echo "Records from the Database<br/>";

       foreach ($user as $key) {
                        echo $key->USER_NAME."<br/>";
                }
        
 ?>