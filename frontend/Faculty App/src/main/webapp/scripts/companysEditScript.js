document.addEventListener("DOMContentLoaded", function () {

    const statusModal = document.getElementById("statusModal");
    if (!statusModal) return;

    statusModal.addEventListener("show.bs.modal", function (event) {

        const button = event.relatedTarget;
        if (!button) return;

        const companyId = button.getAttribute("data-id");
        const companyName = button.getAttribute("data-name");
        const action = button.getAttribute("data-status");

        const message = document.getElementById("modalMessage");
        const confirmBtn = document.getElementById("modalConfirmBtn");

        document.getElementById("modalCompanyId").value = companyId;
        document.getElementById("modalAction").value = action;

        if (action === "activate") {
            message.innerHTML =
                "Are you sure you want to activate the company <strong>"
                + companyName + "</strong>?";

            confirmBtn.className = "btn btn-success";
            confirmBtn.textContent = "Activate";
        } else {
            message.innerHTML =
                "Are you sure you want to deactivate the company <strong>"
                + companyName + "</strong>?";

            confirmBtn.className = "btn btn-danger";
            confirmBtn.textContent = "Deactivate";
        }

    });

});