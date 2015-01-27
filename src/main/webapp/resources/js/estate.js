$(document).ready(function () {
    $('#addEstateCategoryBtn').click(function() {
        var category = $('#addEstateCategory').val();
        var url = $('#addEstateCategory').attr("url");
        if (category.trim().length == 0) {
            alert('Название категории пустое!');
            return false;
        }
        $.ajax({
            type: "POST",
            data: "name=" + category,
            url: url,
            beforeSend: function() {
                $("#loader").show("fast");
            },
            success: function(data) {
                $("#loader").hide("fast");
                $("#estateCategory .estateCategoryList").remove();
                $("#estateCategory").append(data);
            },
            error: function(err) {
                $("#loader").hide("fast");
                alert("Ошибка на сервере" + err);
            }
        });
    });

    $('#addEstateTypeBtn').click(function() {
        var category = $('#addEstateType').val();
        var url = $('#addEstateType').attr("url");
        if (category.trim().length == 0) {
            alert('Название типа пустое!');
            return false;
        }
        $.ajax({
            type: "POST",
            data: "name=" + category,
            url: url,
            beforeSend: function() {
                $("#loader").show("fast");
            },
            success: function(data) {
                $("#loader").hide("fast");
                $("#estateType .estateTypeList").remove();
                $("#estateType").append(data);
            },
            error: function(err) {
                $("#loader").hide("fast");
                alert("Ошибка на сервере" + err);
            }
        });
    });

    $('#addDistrictBtn').click(function() {
        var category = $('#addDistrict').val();
        var url = $('#addDistrict').attr("url");
        if (category.trim().length == 0) {
            alert('Название района пустое!');
            return false;
        }
        $.ajax({
            type: "POST",
            data: "name=" + category,
            url: url,
            beforeSend: function() {
                $("#loader").show("fast");
            },
            success: function(data) {
                $("#loader").hide("fast");
                $("#district .districtList").remove();
                $("#district").append(data);
            },
            error: function(err) {
                $("#loader").hide("fast");
                alert("Ошибка на сервере" + err);
            }
        });
    });

    $('#addEstateBtn').click(function () {
        $('#addEstateForm').show();
    });

    $('#cutDate').attachDatepicker();

    $('#deleteEstateBtn').click(function () {
        $('#deleteEstateForm').show();
    });

    $('#addEstateSubmit').click(function() {
        var url = $('#addEstateForm').attr("action");
        $.ajax({
            type: "POST",
            data: $('#addEstateForm').serialize(),
            url: url,
            beforeSend: function() {
                $("#loader").show("fast");
            },
            success: function(data) {
                $("#loader").hide("fast");
                $("#estateListTable").remove();
                $("#emptyEstateList").remove();
                $("#estateListDiv").append(data);
                $('#addEstateForm').hide();
            },
            error: function(err) {
                $("#loader").hide("fast");
                alert("Ошибка на сервере" + err);
            }
        });
        return false;
    });

    $('#closeEstateSubmit').click(function() {
        $('#addEstateForm').hide();
    });

    $('#deleteEstateFormClose').click(function() {
        $('#deleteEstateForm').hide();
    });

});