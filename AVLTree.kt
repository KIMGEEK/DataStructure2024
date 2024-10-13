import java.lang.Math.max

class AVLTree <T:Comparable<T>> {
    var root:AVLNode<T>? = null
    override fun toString() = root?.toString() ?: "empty tree"

/*    private fun leftRotate(node: AVLNode<T>?): AVLNode<T>? {
        val pivot = node.rightChild!!
        node.rightChild = pivot.leftChild
        pivot.leftChild = node
        node.height = max(node.leftHeight, node.rightHeight) + 1
        pivot.height = max(pivot.leftHeight, pivot.rightHeight) + 1
        return pivot
    }*/
/*    private fun rightRotate(node: AVLNode<T>?): AVLNode<T>? {
        val pivot = node.leftChild!!
        node.leftChild = pivot.rightChild
        pivot.rightChild = node
        node.height = max(node.rightHeight, node.rightHeight) + 1
        pivot.height = max(pivot.leftHeight, pivot.rightHeight) + 1
        return pivot
    }*/
}