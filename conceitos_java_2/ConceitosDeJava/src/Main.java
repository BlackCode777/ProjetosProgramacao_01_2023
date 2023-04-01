import Account.Account;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        /* Criando a classe e o método da classe */
        Account myAccount = new Account();
        System.out.println(myAccount.getname());

        /* criando o método setname() */
        String theName = input.nextLine();
        myAccount.setName(theName);
        System.out.println(myAccount.setName(theName));

    }

    public static <T, A> T[] castArray(T[] target, A[] array) {
        for( int i = 0; i<array.length; i++ ){
            target[i] = (T) array[i];
        }
        return target;
    }


}