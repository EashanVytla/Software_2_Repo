import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ProgramSkeleton {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ProgramSkeleton() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        /*
         * Put your main program code here
         */
        BinaryTree<Integer> r = new BinaryTree1<>();
        r.assemble(null, r, r);

    }


    /**
     * Returns whether the given tree is balanced.
     *
     * @ensures isBalanced = [true if t is balanced, false otherwise]
     */
    private static <T> boolean isBalanced(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";
        boolean balanced = true;
        if(t.size() != 0){
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T root = t.disassemble(left, right);

            if(Math.abs(right.height() - left.height()) <= 1){
                balanced = isBalanced(left) && isBalanced(right);
            }else{
                balanced = false;
            }
            t.assemble(root,left,right);
        }

        return balanced;
    }
}

