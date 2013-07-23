$(document).ready(function () {

    $('a.get-location').click(function (e) {
        e.preventDefault();

        // check if browser supports geolocation
        if (navigator.geolocation) {

            // get user's current position
            navigator.geolocation.getCurrentPosition(function (position) {

                var form = document.getElementById('locationForm')

                // get latitude and longitude
                var latitude = position.coords.latitude;
                var longitude = position.coords.longitude;

                // set hidden variables
                document.getElementById('locationLongitude').value = longitude
                document.getElementById('locationLatitude').value = latitude

                // display
                $('div.location').text(latitude + ", " + longitude);


            });
        }

        // fallback for browsers without geolocation
        else {

            // get manually entered postcode
            startingLocation = $('.manual-location').val();

            // if user has entered a starting location, send starting location and destination to goToGoogleMaps function
            if (startingLocation != '') {
                goToGoogleMaps(startingLocation, destination);
            }
            // else fade in the manual postcode field
            else {
                $('.no-geolocation').fadeIn();
            }

        }
    })

})