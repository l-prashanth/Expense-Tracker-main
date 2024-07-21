
function validateForm() {
    // Check if either "Expense" or "Card Name" is selected
    let expenseSelected = document.getElementById('billType').value !== '';
    let cardSelected = document.getElementById('cardType').value !== '';

    // Set 'required' attribute on the "month" field accordingly
    let monthField = document.getElementById('month');
    let yearField = document.getElementById('year');

    monthField.required = expenseSelected || cardSelected;
    yearField.required = expenseSelected || cardSelected;

    console.log('Month required:', monthField.required);
    console.log('Year required:', yearField.required);

    // Check if the required fields are filled
    if (!monthField.checkValidity() || !yearField.checkValidity()) {
        // If not, prevent form submission
        alert('Please fill in the required fields.');
        return false;
    }

    // You can add additional validation logic here if needed
    return true; // Return true to allow form submission, or false to prevent it
}

function toggleFieldsFilter() {
    let allFieldsDiv = document.getElementById('allFields');
    let toggleButton = document.getElementById('toggleButton');

    if (allFieldsDiv.style.display === 'none') {
        allFieldsDiv.style.display = 'block';
        toggleButton.textContent = 'CLOSE FILTER';
    } else {
        allFieldsDiv.style.display = 'none';
        toggleButton.textContent = 'OPEN FILTER';
    }

    // Hook the validateForm function to the form submission event
    document.getElementById("filterForm").addEventListener("submit", function(event) {
        if (!validateForm()) {
            // Prevent form submission if validation fails
            event.preventDefault();
        }
    });
}
function showFields() {
    let selectedOption = document.getElementById("options").value;// let filterOption = document.getElementById("filterOptionsSelect").value;


    document.getElementById("creditExpenseFields").style.display = (selectedOption === "creditExpense") ? "block" : "none";
    // document.getElementById("creditCardAmount").style.backgroundColor = "#e10b0b";

    document.getElementById("debitExpenseFields").style.display = (selectedOption === "debitExpense") ? "block" : "none";
    document.getElementById("addBalanceFields").style.display = (selectedOption === "addBalance") ? "block" : "none";
    document.getElementById("repaymentFields").style.display = (selectedOption === "repayment") ? "block" : "none";
}


$(function () {
    $("#creditDate").datepicker();

});
$(function () {
    $("#debitDate").datepicker();
});
$(function () {
    $("#creditRepaymentDate").datepicker();
});

$(function () {
    $("#addBalanceDate").datepicker();
});

