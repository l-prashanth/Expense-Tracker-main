
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
// let expenseInfo = document.getElementById("options").value;
document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.expense-info, .balance-info, .axis-ace-info, .one-card-info, .axis-flipkart-info, .hdfc-swiggy-info').forEach(function(element) {
        // Function to show/hide elements
        function toggleElementVisibility() {
            const hiddenText = element.querySelector('.hidden-text');
            const statusLabel = element.querySelector('.statusLabel');

            if (hiddenText && statusLabel) {
                hiddenText.classList.add('show'); // Show the hidden text
                statusLabel.classList.add('hide'); // Hide the status label

                setTimeout(function() {
                    hiddenText.classList.remove('show'); // Hide the hidden text after 2 seconds
                    statusLabel.classList.remove('hide'); // Show the status label again
                }, 2000); // 2000 milliseconds = 2 seconds
            }
        }

        // Add event listeners for both mouseover and touchstart
        element.addEventListener('mouseover', toggleElementVisibility);
        element.addEventListener('touchstart', function(event) {
            event.preventDefault(); // Prevent default touch behavior
            toggleElementVisibility();
        });
    });
});

