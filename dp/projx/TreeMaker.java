/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projx;

/**
 * TreeMaker. Build an entry tree.
 *
 * @author user
 */
public class TreeMaker
{
     /**
      * An instance of a javax.swing.JTree object.
      */
     javax.swing.JTree tree = null;
     /**
      * A linked list with entries.
      */
     EntryLinkedList mList = null;

     /**
      * Constructor.
      *
      * @param t Instance of a javax.swing.JTree
      * @param l Instance of EntryLinkedList class, with entries.
      */
     public TreeMaker(javax.swing.JTree t, EntryLinkedList l)
     {
        tree = t;
        mList = l;

        javax.swing.tree.DefaultMutableTreeNode nn =
                (javax.swing.tree.DefaultMutableTreeNode) t.getModel().getRoot();
        // tree.setRootVisible(false);
        buildTree(nn, l);
     }

     /**
      * Build the tree.
      *
      * @param parent A node to which other nodes will be attach.
      * @param list A list of entries.
      */
     private void buildTree(javax.swing.tree.DefaultMutableTreeNode parent, EntryLinkedList list)
     {

        // Loop through
        String entryName = "projx.FFEntry";
        int i = 0;
        while ( i < list.size())
        {
           if (list.get(i).getClass().getName().matches(entryName))  // now it's an entry
           {
               attachNode(parent, new javax.swing.tree.DefaultMutableTreeNode(list.get(i)));
           }
           else // Other wise
           {
              // Create the new child node. The content is the linked list.
              javax.swing.tree.DefaultMutableTreeNode child =
                      new javax.swing.tree.DefaultMutableTreeNode(list.get(i));
              // Attach the child node to the tree
              attachNode(parent, child);
              // Call the method again to construct the child tree.
              buildTree(child, (projx.EntryLinkedList) list.get(i));
           }

            i++;
        }


     }

     /**
      * Attach a node to another node, one serves as the parent.
      *
      * @param parentNode The parent's node
      * @param childNode Node to attach.
      */
     private void attachNode (
             javax.swing.tree.DefaultMutableTreeNode parentNode,
             javax.swing.tree.DefaultMutableTreeNode childNode)
     {
        javax.swing.tree.DefaultTreeModel tm =
                (javax.swing.tree.DefaultTreeModel) tree.getModel();
        tm.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
        tree.scrollPathToVisible(new javax.swing.tree.TreePath(childNode.getPath()));
     }

     /**
      * Get the item attached to the selected node on the tree.
      *
      * @return The object attached to the node, either a linked list or an entry.
      */
     public Object getSelectedNode ()
     {
        // Figure out which node is selected
        // First we get the path ...
        javax.swing.tree.TreePath tp = tree.getSelectionPath();
        // If that is null, i.e. no node is selected, we return null
        if (tp == null){return null;}
        else
        {
           // Else we get access to the selected node
           javax.swing.tree.DefaultMutableTreeNode tn =
                (javax.swing.tree.DefaultMutableTreeNode) tp.getLastPathComponent();
           // Then we grab the object at that node, and return that.
           return tn.getUserObject();
        }

     }

}
