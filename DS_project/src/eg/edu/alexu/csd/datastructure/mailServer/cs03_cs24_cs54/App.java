package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IApp;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;

public class App implements IApp{

    @Override
    public boolean signin(String email, String password) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean signup(IContact contact) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public IMail[] listEmails(int page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteEmails(ILinkedList mails) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moveEmails(ILinkedList mails, IFolder des) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean compose(IMail email) {
        // TODO Auto-generated method stub
        return false;
    }

}
