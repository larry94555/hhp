  $(function() {
    var availableTags = [
      "Alex",
      "Corrina",
      "Dennis",
      "Doug",
      "Larry",
      "Nicole",
      "Pavel",
      "Ryan",
      "Tom"
    ];
    $( "#names" ).autocomplete({
      source: availableTags
    });
  });