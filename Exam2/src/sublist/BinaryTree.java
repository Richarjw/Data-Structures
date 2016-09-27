//package sublist;
//
//public class BinaryTree {
//	public String maxSubtree(){
//		Info info = root.maxSubtree();
//		return "" + info.where + info.maxSum;
//	}
//	class Info{
//		int sum;
//		int maxSum;
//		char where;
//		public Info(int sum, int maxSum, char where){
//			this.sum = sum;
//			this.maxSum = maxSum;
//			this.where = where;
//		}
//	}
//	public class BinaryNode{
//		public Info maxSubtree(){
//			if(this == NULL_NODE){
//				return new Info(0,0, '$');
//				}
//			Info leftInfo = left.maxSubtree();
//			Info rightInfo = right.maxSubtree();
//			int mySum = leftInfo.sum + rightInfo.sum + this.value;
//			
//			//Figure out which is largest:
//			//1. left subtree max sum:
//			if(leftInfo.maxSum >- rightInfo.maxSum && leftInfo.maxSum >= mySum){
//				return new Info(mySum, leftInfo.maxSum, leftInfo.where);
//				
//			}
//			//2. current nodes sum largest:
//			if(mySum >= rightInfo.maxSum){
//				return new Info(mySum, mySum, label);
//			}
//			//3. the right subtree's maxSum
//			return new Info(mySum, rightInfo.maxSum,rightInfo.where);
//			}
//	}
//		}
//	}
//}
