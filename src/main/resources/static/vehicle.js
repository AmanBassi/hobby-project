(function () {

    const getVehicles = async () => {
        const tableBody = document.querySelector("tbody");
        tableBody.innerHTML = "";
        const vehicles = await axios.get("/vehicle/getAll");
        vehicles.data.forEach(vehicle => {
            renderVehicle(vehicle)
        });
    }

    const renderVehicle = vehicle => {
        const tableBody = document.querySelector("tbody");

        const tableRow = document.createElement("tr");

        tableRow.appendChild(createOpenVehicleLink(vehicle.id));
        tableRow.appendChild(createTableCell(vehicle.id));
        tableRow.appendChild(createTableCell(vehicle.registration));
        tableRow.appendChild(createTableCell(vehicle.make));
        tableRow.appendChild(createTableCell(vehicle.model));
        tableRow.appendChild(createTableCell(vehicle.colour));
        tableRow.appendChild(createTableCell(vehicle.horsePower));
        tableRow.appendChild(createUpdateButton(vehicle));
        tableRow.appendChild(createDeleteButton(vehicle.id));

        tableBody.appendChild(tableRow);
    }

    const createTableCell = (data) => {
        const cell = document.createElement("td");
        cell.innerText = data;
        cell.className = "align-middle";
        return cell;
    }

    const createOpenVehicleLink = (vehicleId) => {
        const cell = document.createElement("td");
        const openLink = document.createElement("a");
        openLink.innerText = "Open";
        openLink.className = "btn btn-success";
        openLink.href = `task.html?id=${vehicleId}`

        cell.appendChild(openLink)
        return cell;
    }

    const createUpdateButton = (vehicle) => {
        const cell = document.createElement("td");
        const updateButton = document.createElement("button");
        updateButton.innerText = "Update";
        updateButton.className = "btn btn-primary";
        updateButton.setAttribute("type", "button");
        updateButton.setAttribute("data-bs-toggle", "modal");
        updateButton.setAttribute("data-bs-target", "#updateVehicleModal");
        updateButton.addEventListener("click", function (event) {
            document.getElementById("updateRegistration").value = vehicle.registration;
            document.getElementById("updateMake").value = vehicle.make;
            document.getElementById("updateModel").value = vehicle.model;
            document.getElementById("updateColour").value = vehicle.colour;
            document.getElementById("updateHorsePower").value = vehicle.horsePower;
            document.getElementById("updateVehicleButton").setAttribute("vehicleId", vehicle.id);
        });

        cell.appendChild(updateButton)
        return cell;
    }

    const createDeleteButton = (vehicleId) => {
        const cell = document.createElement("td");
        const deleteButton = document.createElement("button");
        deleteButton.innerText = "Delete";
        deleteButton.className = "btn btn-danger";
        deleteButton.setAttribute("type", "submit");
        deleteButton.addEventListener("click", function () {
            deleteVehicle(vehicleId);
        });

        cell.appendChild(deleteButton);
        return cell;
    }

    getVehicles();

    const deleteVehicle = async (id) => {
        const result = await axios.delete(`/vehicle/delete/${id}`);
        getVehicles();
    }

    document.getElementById("createVehicleForm").addEventListener("submit", function (event) {
        event.preventDefault();
        if (!this.checkValidity()) {
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

        axios.post("/vehicle/create", data)
            .then(function (response) {
                console.log(response);
                var myModalElement = document.getElementById('createVehicleModal');
                var modal = bootstrap.Modal.getInstance(myModalElement);
                modal.hide();

                document.getElementById("createVehicleForm").classList.remove('was-validated');
                document.getElementById("createVehicleForm").reset();
                getVehicles();
            })
            .catch(function (error) {
                console.log(error);
            });
        console.log(this);
    });

    document.getElementById("updateVehicleForm").addEventListener("submit", function (event) {
        event.preventDefault();
        if (!this.checkValidity()) {
            event.stopPropagation();
            console.log("form was not valid");
            return;
        }
        this.classList.add('was-validated')
        console.log("form was valid");

        const vehicleId = document.getElementById("updateVehicleButton").getAttribute("vehicleId");
        const data = {
            registration: this.updateRegistration.value,
            make: this.updateMake.value,
            model: this.updateModel.value,
            colour: this.updateColour.value,
            horsePower: this.updateHorsePower.value
        }

        axios.put(`/vehicle/update/${vehicleId}`, data)
            .then(function (response) {
                console.log(response);
                var myModalElement = document.getElementById('updateVehicleModal');
                var modal = bootstrap.Modal.getInstance(myModalElement);
                modal.hide();

                document.getElementById("updateVehicleForm").classList.remove('was-validated');
                document.getElementById("updateVehicleForm").reset();
                getVehicles();
            })
            .catch(function (error) {
                console.log(error);
            });
        console.log(this);
    });
})();