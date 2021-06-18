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

    const getTasks = async (id) => {
        const tableBody = document.querySelector("tbody");
        tableBody.innerHTML = "";
        const tasks = await axios.get(`/task/getAllByVehicle/${id}`);
        tasks.data.forEach(task => {
            renderTask(task);
        });
    }

    const deleteTask = async (id) => {
        const result = await axios.delete(`/task/delete/${id}`);
        getTasks(vehicleId);
    }

    const createTableCell = (data) => {
        const cell = document.createElement("td");
        cell.innerText = data;
        cell.className = "align-middle";
        return cell;
    }

    const createUpdateButton = (task) => {
        const cell = document.createElement("td");
        const updateButton = document.createElement("button");
        updateButton.innerText = "Update";
        updateButton.className = "btn btn-primary";
        updateButton.setAttribute("type", "button");
        updateButton.setAttribute("data-bs-toggle", "modal");
        updateButton.setAttribute("data-bs-target", "#updateTaskModal");
        updateButton.addEventListener("click", function (event) {
            document.getElementById("updateName").value = task.name;
            document.getElementById("updateDueDate").value = task.dueDate;
            document.getElementById("updateTaskButton").setAttribute("taskId", task.id);
        });

        cell.appendChild(updateButton)
        return cell;
    }

    const createDeleteButton = (taskId) => {
        const cell = document.createElement("td");
        const deleteButton = document.createElement("button");
        deleteButton.innerText = "Delete";
        deleteButton.className = "btn btn-danger";
        deleteButton.setAttribute("type", "submit");
        deleteButton.addEventListener("click", function () {
            deleteTask(taskId);
        });

        cell.appendChild(deleteButton);
        return cell;
    }

    const renderTask = task => {
        const tableBody = document.querySelector("tbody");

        const tableRow = document.createElement("tr");

        tableRow.appendChild(createTableCell(task.id));
        tableRow.appendChild(createTableCell(task.name));
        tableRow.appendChild(createTableCell(task.dueDate));
        tableRow.appendChild(createUpdateButton(task));
        tableRow.appendChild(createDeleteButton(task.id));

        tableBody.appendChild(tableRow);
    }

    const vehicleId = getVehicleId();
    getVehicle(vehicleId);
    getTasks(vehicleId);

    document.getElementById("createTaskForm").addEventListener("submit", function (event) {
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
            dueDate: this.dueDate.value
        }

        axios.post(`/task/create/${vehicleId}`, data)
            .then(function (response) {
                console.log(response);
                var myModalElement = document.getElementById('createTaskModal');
                var modal = bootstrap.Modal.getInstance(myModalElement);
                modal.hide();

                document.getElementById("createTaskForm").classList.remove('was-validated');
                document.getElementById("createTaskForm").reset();
                getTasks(vehicleId);
            })
            .catch(function (error) {
                console.log(error);
            });
        console.log(this);
    });

    document.getElementById("updateTaskForm").addEventListener("submit", function (event) {
        event.preventDefault();
        if (!this.checkValidity()) {
            event.stopPropagation();
            console.log("form was not valid");
            return;
        }
        this.classList.add('was-validated')
        console.log("form was valid");

        const taskId = document.getElementById("updateTaskButton").getAttribute("taskId");
        const data = {
            name: this.updateName.value,
            dueDate: this.updateDueDate.value
        }

        axios.put(`/task/update/${taskId}`, data)
            .then(function (response) {
                console.log(response);
                var myModalElement = document.getElementById('updateTaskModal');
                var modal = bootstrap.Modal.getInstance(myModalElement);
                modal.hide();

                document.getElementById("updateTaskForm").classList.remove('was-validated');
                document.getElementById("updateTaskForm").reset();
                getTasks(vehicleId);
            })
            .catch(function (error) {
                console.log(error);
            });
        console.log(this);
    });

})();