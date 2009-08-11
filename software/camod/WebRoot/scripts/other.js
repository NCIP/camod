
function chkOther(vocab, otherVocab) {
	
	if( vocab.value == 'Other' ) {
		otherVocab.disabled = false;
	}
	else {
		otherVocab.value = '';
		otherVocab.disabled = true;
	}
}