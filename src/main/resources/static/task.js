(function () {

    const getVehicleId = () => {
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
        return urlParams.get('id');
    }

    const getVehicle = async (id) => {

        const vehicle = await axios.get(`/vehicle/getById/${id}`);

        document.getElementById("vehicleId").innerText = vehicle.data.id;
        document.getElementById("vehicleRegistration").innerText = vehicle.data.registration;
        document.getElementById("vehicleMake").innerText = vehicle.data.make;
        document.getElementById("vehicleModel").innerText = vehicle.data.model;
        document.getElementById("vehicleColour").innerText = vehicle.data.colour;
        document.getElementById("vehicleHorsePower").innerText = vehicle.data.horsePower;
    }

    const vehicleId = getVehicleId();
    getVehicle(vehicleId);


    document.getElementById("createVehicleTaskForm").addEventListener("submit", function (event) {
        event.preventDefault();
        if (!this.checkValidity()) {
            event.stopPropagation();
            console.log("form was not valid");
            return;
        }
        this.classList.add('was-validated')
        console.log("form was valid");

        const data = {
            name: this.name.value,
            dueDate: this.dueDate.value,
            vehicle: { id: vehicleId }
        }

        axios.post("/vehicletask/create", data)
            .then(function (response) {
                console.log(response);
                var myModalElement = document.getElementById('createVehicleTaskModal');
                var modal = bootstrap.Modal.getInstance(myModalElement);
                modal.hide();

                document.getElementById("createVehicleTaskForm").classList.remove('was-validated');
                document.getElementById("createVehicleTaskForm").reset();
            })
            .catch(function (error) {
                console.log(error);
            });
        console.log(this);
    });

})();