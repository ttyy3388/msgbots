function getDoumChar(lastChar)
{
	let data = lastChar.charCodeAt() - 0xAC00;
	if (data < 0 || data > 11171) return ;

	const RIEUL_TO_NIEUN = [4449, 4450, 4457, 4460, 4462, 4467];
	const RIEUL_TO_IEUNG = [4451, 4455, 4456, 4461, 4466, 4469];
	const NIEUN_TO_IEUNG = [4455, 4461, 4466, 4469];
	
	let	onset = Math.floor(data / 28 / 21) + 0x1100,
		nucleus = (Math.floor(data / 28) % 21) + 0x1161, 
		coda = (data % 28) + 0x11A7, isDoumChar = false, doumChar;
	
	if (onset == 4357)
	{
		isDoumChar = true;
		(RIEUL_TO_NIEUN.indexOf(nucleus) != -1) ? onset = 4354 : (RIEUL_TO_IEUNG.indexOf(nucleus) != -1) ? onset = 4363 : isDoumChar = false;
	}
	else if (onset == 4354)
	{
		if (NIEUN_TO_IEUNG.indexOf(nucleus) != -1)
		{
			onset = 4363;
			isDoumChar = true;
		}
	}
	if (isDoumChar)
	{
		onset -= 0x1100; nucleus -= 0x1161; coda -= 0x11A7;
		doumChar = String.fromCharCode(((onset * 21) + nucleus) * 28 + coda + 0xAC00);
	}
	
    return doumChar;
};
