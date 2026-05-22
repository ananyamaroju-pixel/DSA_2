public class CO1_Code {

    static class RankNode {

        int key;
        RankNode left, right;
        int size = 1;

        RankNode(int key) {
            this.key = key;
        }
    }

    static int size(RankNode n) {
        return n == null ? 0 : n.size;
    }

    static void updateSize(RankNode n) {

        if (n != null)
            n.size = 1 + size(n.left) + size(n.right);
    }

    /* Return 1-based rank of key
       Tree stored in DESCENDING order
       (root.left.key > root.key > root.right.key)
    */

    static int rankOf(RankNode root, int key) {

        int rank = 1;

        RankNode node = root;

        while (node != null) {

            // HIGHER score -> go LEFT
            if (key > node.key) {

                node = node.left;
            }

            // LOWER score
            else if (key < node.key) {

                rank += size(node.left) + 1;

                node = node.right;
            }

            // FOUND
            else {

                rank += size(node.left);

                return rank;
            }
        }

        return rank;
    }

    public static void main(String[] args) {

        // Manual tree creation based on final AVL tree

        RankNode root = new RankNode(820);

        root.left = new RankNode(910);
        root.right = new RankNode(540);

        root.left.left = new RankNode(990);
        root.left.right = new RankNode(880);

        root.left.left.right = new RankNode(950);

        root.right.left = new RankNode(770);
        root.right.right = new RankNode(510);

        root.right.left.right = new RankNode(730);

        root.right.right.right = new RankNode(460);

        // Update size fields
        updateSize(root.right.right);
        updateSize(root.right.left);
        updateSize(root.left.left);

        updateSize(root.left);
        updateSize(root.right);

        updateSize(root);

        int rank = rankOf(root, 770);

        System.out.println("Rank of 770 = " + rank);
    }
}