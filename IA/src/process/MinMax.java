package process;

public class MinMax {

	 public boolean checkWin() {
	        Node root = tree.getRoot();
	        checkWin(root);
	        return root.getScore() == 1;
	    }

	    private void checkWin(Node node) {
	        List<Node> children = node.getChildren();
	        boolean isMaxPlayer = node.isMaxPlayer();
	        children.forEach(child -> {
	            if (child.getNoOfBones() == 0) {
	                child.setScore(isMaxPlayer ? 1 : -1);
	            } else {
	                checkWin(child);
	            }
	        });
	        Node bestChild = findBestChild(isMaxPlayer, children);
	        node.setScore(bestChild.getScore());
	    }

	    private Node findBestChild(boolean isMaxPlayer, List<Node> children) {
	        Comparator<Node> byScoreComparator = Comparator.comparing(Node::getScore);

	        return children.stream()
	          .max(isMaxPlayer ? byScoreComparator : byScoreComparator.reversed())
	          .orElseThrow(NoSuchElementException::new);
	    }
	}
	
}
