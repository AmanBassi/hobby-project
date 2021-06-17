(function () {

    const getVehicle = async () => {
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
        const id = urlParams.get('id');
        const vehicle = await axios.get(`/vehicle/getById/${id}`);

        document.getElementById("vehicleId").innerText = vehicle.data.id;
        document.getElementById("vehicleRegistration").innerText = vehicle.data.registration;
        document.getElementById("vehicleMake").innerText = vehicle.data.make;
        document.getElementById("vehicleModel").innerText = vehicle.data.model;
        document.getElementById("vehicleColour").innerText = vehicle.data.colour;
        document.getElementById("vehicleHorsePower").innerText = vehicle.data.horsePower;
    }

    getVehicle();

})();