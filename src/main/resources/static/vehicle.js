(function () {

    const tableBody = document.querySelector("tbody");

    const getVehicles = async () => {
        const vehicles = await axios.get("/getAll");
        vehicles.data.forEach(vehicle => {
            renderVehicle(vehicle)
        });
    }

    const renderVehicle = vehicle => {
        const tableRow = document.createElement("tr");

        createTableCell(tableRow, vehicle.id);

        createTableCell(tableRow, vehicle.registration);

        createTableCell(tableRow, vehicle.make);

        createTableCell(tableRow, vehicle.model);

        createTableCell(tableRow, vehicle.colour);

        createTableCell(tableRow, vehicle.horsePower);

        tableBody.appendChild(tableRow);
    }

    const createTableCell = (tableRow, data) => {
        const cell = document.createElement("td");
        cell.innerText = data;
        tableRow.appendChild(cell);
    }

    getVehicles();

    document.getElementById("createVehicleForm").addEventListener("submit", function (event) {
        if (!this.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
            console.log("form was not valid");
            return;
        }
        this.classList.add('was-validated')
        console.log("form was valid");

        const data = {
            registration: this.registration.value,
            make: this.make.value,
            model: this.model.value,
            colour: this.colour.value,
            horsePower: this.horsePower.value
        }

        axios.post("/create", data)
            .then(function (response) {
                console.log(response);
                var myModalElement = document.getElementById('exampleModal');
                var modal = bootstrap.Modal.getInstance(myModalElement);
                modal.hide();
                document.getElementById("createVehicleForm").classList.remove('was-validated');
            })
            .catch(function (error) {
                console.log(error);
            });

        console.log(this);
    });
})();