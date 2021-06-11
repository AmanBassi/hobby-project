(function () {
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
                var myModalEl = document.getElementById('exampleModal');
                var modal = bootstrap.Modal.getInstance(myModalEl);
                modal.hide();
                document.getElementById("createVehicleForm").classList.remove('was-validated');
            })
            .catch(function (error) {
                console.log(error);
            });

        console.log(this);
    });
})();