document.querySelector('.add-detail').onclick = function() {
	var holder, fields, index = 0;

	index = document.querySelectorAll('.input-detail').length + 1;
	holder = document.querySelector('#input-detail-template').content
			.cloneNode(true);
	fields = holder.querySelectorAll('.field');
	fields[0].dataset.fieldName = 'detail_goods_id' + index;
	fields[0].innerHTML = '';
	fields[0].contentEditable = true;
	fields[0].classList.add('editing');
	fields[1].dataset.fieldName = 'detail_price' + index;
	fields[1].innerHTML = '';
	fields[1].contentEditable = true;
	fields[1].classList.add('editing');
	fields[2].dataset.fieldName = 'detail_goods_number' + index;
	fields[2].innerHTML = '';
	fields[2].contentEditable = true;
	fields[2].classList.add('editing');
	document.querySelector('#editable').appendChild(holder);
};

jQuery('#editable').on(
		'click',
		'.delete-detail',
		function(evt) {
			var root = document.querySelector('#editable');

			root.removeChild(evt.target.parentNode);
			Array.from(root.querySelectorAll('.input-detail')).forEach(
					function(input, index) {
						var fields;

						fields = holder.querySelectorAll('.field');
						fields[0].dataset.fieldName = 'detail_goods_id'
								+ (index + 1);
						fields[1].dataset.fieldName = 'detail_price'
								+ (index + 1);
						fields[2].dataset.fieldName = 'detail_goods_number'
								+ (index + 1);
					});
		});