package org.beuwi.simulator.platform.application.views.dialogs;

public class SettingsDialog
{
	/* private AnchorPane root;

	private Button btnOk;
	private Button btnNo;

	public void initialize()
	{
		dialog = new DialogBoxView(DialogBoxType.NONE);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SettingsDialog.class.getResource("/forms/SettingsDialog.fxml"));
		loader.setController(null);

		Region root = null;

		try
		{
			root = loader.load();
		}
		catch (Exception e)
		{
			new ShowErrorDialog(e).display();
		}

		nameSpace = loader.getNamespace();

		root = loader.getRoot();

		btnOk = dialog.getOkButton();
		btnNo = dialog.getNoButton();

		btnOk.setDisable(true);
		btnOk.setText("OK");
		btnNo.setText("Cancel");

		dialog.setUseButton(true, true);
		dialog.setContent(root);
		dialog.setTitle("Settings");
		dialog.create();
	}

	public ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}

	public AnchorPane getRoot()
	{
		return root;
	}

	public void display()
	{
		dialog.show();
	} */
}