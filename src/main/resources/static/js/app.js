
    function create_theme(){
        $("#addBtnTheme").attr('disabled',true);

        var form = $('#createThemeForm');
        var msg = form.serialize();
        $.ajax({
            type: 'POST',
            url: '/create',
            data: msg,
            success: function(theme) {
                $('#themeId').val(theme.id);
                $('#resultTheme').append(theme.name);
            },
            error:  function(){
                alert('Ошибка!');
            }
        });
        clearInputs();
    }

    function create_question(){

        var form = $('#createQuestionForm');
        var msg = form.serialize();
        $.ajax({
            type: 'POST',
            url: '/question',
            data: msg,
            success: function(question) {
                $('#resultQuestion').append('<div class="css-label">'+ question.name +'</div>');
            },
            error:  function(){
                alert('Ошибка!');
            }
        });
        clearInputs();
    }

    function clearInputs() {
        $('input[id*="Input"]').each(function () {
            $(this).val('');
        });
    }

