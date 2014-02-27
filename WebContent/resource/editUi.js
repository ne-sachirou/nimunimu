// ES6
if (!Array.from) {
	Array.from = function(list) {
		return [].slice.call(list);
	};
}

/**
 * @constructor
 */
function EditUi() {
	this._initNodes();
}

/**
 * @return {EditUi}
 */
EditUi.prototype.start = function() {
	var me = this;

	this.editButton.onclick = function() {
		me._startEditing();
	};
	this.deleteButton.onclick = function() {
		me._finishEditing();
		me._delete().then(function() {
			history.back();
		}, function(err) {
			alert(err.message);
		});
	};
	this.saveButton.onclick = function() {
		me._finishEditing();
		me._save().then(function() {
			location.reload();
		}, function(err) {
			alert(err.message);
		});
	};
	this.cancelButton.onclick = function() {
		me._finishEditing();
		location.reload();
	};
	return this;
};

EditUi.prototype._save = function() {
	return this._requestUpdate('PUT', 'field');
};

EditUi.prototype._delete = function() {
	return this._requestUpdate('DELETE', 'pk');
};

EditUi.prototype._requestUpdate = function(requestType, fieldClassName) {
	var dataStr, req;

	dataStr = 'requestType=' + requestType;
	Array.from(this.containerNode.querySelectorAll('.' + fieldClassName))
			.forEach(function(node) {
				var k, v;

				k = node.dataset.fieldName;
				v = node.textContent;
				dataStr += '&' + k + '=' + encodeURIComponent(v);
			});
	req = new XMLHttpRequest();
	req.open('POST', location.href, true);
	req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	req.send(dataStr);
	return new Promise(function(resolve, reject) {
		req.onreadystatechange = function() {
			if (req.readyState === 4) {
				if (req.status !== 200) {
					return reject(new Error(req.statusText + '\n'
							+ req.responseText));
				}
				reject();
			}
		};
	});
};

EditUi.prototype._initNodes = function() {
	this.containerNode = document.getElementById('editable');
	this.editButton = this.containerNode.querySelector('.edit');
	this.deleteButton = this.containerNode.querySelector('.delete');
	this.saveButton = this.containerNode.querySelector('.save');
	this.cancelButton = this.containerNode.querySelector('.cancel');
	this.saveButton.style.display = 'none';
	this.cancelButton.style.display = 'none';
};

EditUi.prototype._startEditing = function() {
	this.editButton.style.display = 'none';
	this.deleteButton.style.display = 'none';
	this.saveButton.style.display = 'inline';
	this.cancelButton.style.display = 'inline';
	Array.from(this.containerNode.querySelectorAll('.field:not(.pk)')).forEach(
			function(node) {
				node.contentEditable = true;
				node.classList.add('editing');
			});
};

EditUi.prototype._finishEditing = function() {
	this.editButton.style.display = 'inline';
	this.deleteButton.style.display = 'inline';
	this.saveButton.style.display = 'none';
	this.cancelButton.style.display = 'none';
	Array.from(this.containerNode.querySelectorAll('.field')).forEach(
			function(node) {
				node.contentEditable = false;
				node.classList.remove('editing');
			});
};