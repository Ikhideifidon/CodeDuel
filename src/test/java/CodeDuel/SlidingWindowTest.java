package CodeDuel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class SlidingWindowTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void maximumSumSubarray() {
        int[] nums = {-1, 2, 3, 1, -3, 2};
        int k = 4;
        System.out.println(Arrays.toString(SlidingWindow.maximumSumSubarray(nums, k)));
    }

    @Test
    void subarrayWithDesiredSum() {
        int[] nums = {1, 7, 4, 3, 1, 2, 1, 5, 1};
        int desired = 7;
        List<int[]> result = SlidingWindow.subarrayWithDesiredSum(nums, desired);
        for (int[] array : result)
            System.out.println(Arrays.toString(array));
    }

    @Test
    void subarrayWithMaximumContinuousOnes() {
        int[] nums = {0, 1, 0, 1, 0, 0, 1, 1};
        int flip = 3;
        System.out.println(Arrays.toString(SlidingWindow.subarrayWithMaximumContinuousOnes(nums, flip)));
    }

    @Test
    void continuousOnes() {
        int[] nums = {0, 1, 0, 1, 0, 0, 1, 1};
        int flip = 3;
        System.out.println(SlidingWindow.continuousOnes(nums, flip));
    }

    @Test
    void shortestSubstring() {
        String s = "fa4achba4c";
        String word = "abcc";
        System.out.println(SlidingWindow.shortestSubstring(s, word));
    }

    @Test
    void lengthOfLongestSubstring() {
        String s = "aabbbac";
        System.out.println(SlidingWindow.lengthOfLongestSubstring(s));
    }

    @Test
    void findSubstring() {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        System.out.println(SlidingWindow.findSubstring(s, words));
    }

    @Test
    void minWindow() {
        String s = "FAADOBECBDEBANC";
        String t = "ABBC";
        System.out.println(SlidingWindow.minWindow(s, t));

    }

    @Test
    void findRepeatedDnaSequences() {
        String s = "AAAAAGGGGGTGAAAAAGGGGG";
        System.out.println(SlidingWindow.findRepeatedDnaSequences(s));
    }

    @Test
    void minSubArrayLen() {
        int[] nums = {1, 1, 1, 1, 1, 1, 1, 1};
        int target = 11;
        System.out.println(SlidingWindow.minSubArrayLen(target, nums));
    }

    @Test
    void containsNearbyDuplicate() {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        System.out.println(SlidingWindow.containsNearbyDuplicate(nums, k));
    }

    @Test
    void constrainedSubsetSum() {
        int[] nums = {10, -2, -10, -5, 20};
        int k = 2;
        Assertions.assertEquals(23, SlidingWindow.constrainedSubsetSum(nums, k));
    }

    @Test
    void containsNearbyAlmostDuplicateBruteForce() {
        int[] nums = {1, 5, 9, 1, 5, 9};
        int indexDiff = 2;
        int valueDiff = 3;
        System.out.println(SlidingWindow.containsNearbyAlmostDuplicateBruteForce(nums, indexDiff, valueDiff));
    }

    @Test
    void containsNearbyAlmostDuplicateOptimized() {
        int[] nums = {1, 2, 3, 1};
        int indexDiff = 3;
        int valueDiff = 0;
        System.out.println(SlidingWindow.containsNearbyAlmostDuplicateOptimized(nums, indexDiff, valueDiff));
    }

    @Test
    void longestSubstring() {
        String s = "bacbcdacacbaaa";
        int k = 3;
        Assertions.assertEquals(3, SlidingWindow.longestSubstring(s, k));
    }

    @Test
    void longestDupSubstring() {
        String s = "mcncanbbabab";
        System.out.println(SlidingWindow.longestDupSubstring(s));
    }

    @Test
    void longestDupSubstringOptimized() {
        String s = "gzhvilxpjrgrrezatnuzihvaezrbvalaovljiscsnbmtrnthyijuxuehuiffeaknaywwhlfgfvrlbzbdpgszfpdetyyokkdocmexbvoklmvvbtslafxoepxlonexfinlirflmfjxtedkctiyvsvqknwxbumollkdbquysfvqexksoxrjzwzplmrkfozpumozednrycmrxkasvmiqprunuaaqqdijfxjulluuyxalmhglggemtbgimowlzwmbtgyezcuwsscxrofosjmeohhcwuhblbgbmrqwmifbitwzxkpzetbmjaqytnvnehtajurbkerzsekykgpzeojftklggqzlcgconzxntmfbehpjchsajpgrcnnnmhkudwvoashlcnltxglqrtwxybeyptgvrsvofmilpefzatucmhhbdwopxnyzqrovbkqslftylbiphfgvjddottgpbjkwikniijdxqrkxllnuxxkzpovuazkiebfkzfaosfdaxefkyevetouvpopcmqxdwnnejbsnexbpvlgxpgdmppzrhklmzioedkbdtyuxlyimogtyfsskuvurjdxxnjeptaacvooyfyvseeyuootkiexqnbyatybimqdkbmbedhyufdedhbvkdtdsqygzahnogadgbnmagtumobzsknihjasqfputkgtjrepwsqxpzbdpimjmqltkqajlmkxyfdpohvumbjmtkcoqbtpfbgrnyvtnxfbzxrrdcocbshyxlmqgmpwnpaskosvcpmnobagvbufmwdzbggemrmwqzvadkojmxljcvumpbwbexlkhfzbzijieimpmtyspeikpjjrcbthptzmxufrgbzpkhnwqmxplwwakhrepoknhanzgrtzcjqrcaovbkmvpbgqshfdtugenluyjfwpnackdnazrwuxguynnmgnxsrmgqsheanicnuupjsekkjbubmlzsowrwdmhcvnijixpbpkjxcddjhbucjjmhzbjngflezexzaamyyywabsavwdasergsxopvexfkyolcgjpopxlpmcokwfymfvkkeejlfihhnieplqutfwajcduajkfokgdhglqswawpvaeiofrlqushjlykcmaaezqylzfrmshjldjkwwlnfdjftwqsyczfnrdfywajhepujqpjhvdoktpvokhivjwzbvyifhxxjmtxitnvgebamsuwttixondcxqmabhaskvgpsoxgghkucgbwylxkdcmyyfmlwqdktvqsxeuvnsxlwiskhnmlemnyodjheovpccnndsjdpnsrxbrqmvhrmjqquiblrcmtpkhdenbsufplctuymidzbedsdqbsxpeuqllluxgwgesgevutvnrmxkqajulkyacqnqlxxmezzmnezwzkhxmxvooqirztzsfftbmahxltyoouhsvxaedsgacvyqwgninsmgdzwdirmsrjhpcxmcerivcnykalvvwrlxynmbnzwjyaebvdusydsolzsvwynlmoawydodomzfescurfjkxantfbsvoitdalpnmkysfjybknczgabfwcnpigzzufmrjquppcglavjpqdhbnategojhymfajimkfdsmadriphaiwcyrqzbxosflkvywauptbkowspvhknrdxuolqjnosnalniutvtsebvfqkqcxtrpgjhwdbcxmxqvrilbrfibweettocslcwkduuoszjhqefbkxcynplgdnwotnyqyeejzjesuxiapqssqrdoveodnbmsvuwimwcqamksazakdrzfflchmtyygramodlvnuxrtgmryzefuemfihgzrcnhwynbhjmwbeyaqoeskrkwrlpcdgvwgcuvrbpawmuicbthocynmtjiffxiiemmyczeyvixeernlfgslmmfpcytgcakmhelukknqxypbcbtkiizexvqptixetojnytvhukzyhjdnarwzzujjatczabhhsfiyjaxgypimlgrspixnwxtmghdhaivigwhbfqmmicuoolftlgvtwwxbpsrhqjmzdnkttzsvgjaloqlmdcsmcukmbmlqwqrvhvdrnwxscgbfibjvrcavzayjmjspnajmukdqczrxagwpojxztflhfykkabksoidyrhrclasmvcacgsdvzzrssmiirjjjvarbdyqxoytradmfdgqsknlucbiahxbhfsdcevclnnbiinjjxelaydqogirhfbsdkexygukrjqsmquvvyovsupxpksmuxxyvbdwgdnvswgazgfnjusxfmtotbzyppytrolmvfymvvwezxontpexhftejbxqosltqawtidhguuykdylvswranfklcncjgvnfwwqprhssyovhylcezvqrdtkudvrqqausrkeoihnerrkvpdsdtnbpltsllgqzifbfkpjmcmvztfdzrrdcwesuhtuxhxabmpnxybtknexscqltybkdkjlvaywngtpkqhyfasswozlzzoqtqofzqlrvshmhtfodoipsrkjbbdaztfvuhpgjfnrlajikjyvxvkhyllknsaqkzxrycpooyvhvnphbysuhfrxzyaltuakrzeasxyqdbkdsvlyngshuknxcdrytrhvogsrzmosyzbmnbadnpetagczhhocascojyzuzwukuxvgkfuepiakepykbvxaavayfawropobdtodhmfkmwdokrsoigeuzgxerksxepfncqykwttkotojmdffjoiznlgpiclacyuzjgkaqaijnummpotptguquzaykiszjczmzbstfatflgcraqavriundgkzrxnqyeqjuqdqstloxnnqctagyunytlxzedclzztzwegnighzixvmkfxabnxumvflmtmtdvfzmvtwduggyteagektibddntwdqetpodgzcmugxulcbfmthgqrgxneqctqbtaensknsggueoibzgvdascsstuoidvbscasigbzlkwkmtkwxdstxjweqfnnnihjfazysghlyefhptqaecggexrfuccetxyivqkoapvxwmyjdekotcszhcvfybutluikyqwhjxswrvloqwmpjsamylpbjjdnbaqitpwrwgwjalamcynojjqfguyutglvsyghggehlbzjkdfodfjebfvpskhhxcnegauwgtbmmnsmyerfaycecprwnvwqmuzdvsjjrffhatfhbhrbczmwkgnmzmqkgvipxfdtkriuekdktraebiqrxsphxkqdlbixxyulwhsfivkwqkiwkzihwphhglwuiwblqxctleyaiqphlblyorzutdxujtufktkgkkslnrvkeejzvizivljvvrvqnsydaotfcnzmrttbimakwphdniksoludxiogjpdmazbnohqrxlodjggmmjnsmzprxkgybjqilsgupnyehtkdcapdkcbcpcjgmzukwxmpntutlwljikjxppecreizqeqxuqolhrzsxfldfhyazoakuwrrpxltbsbczjzqjcuyhydxgstlydyasgykimeebpbpnshflijauctortfwjjfxwodmkkrcqkeoofunszmajfppjrvhbprwhrmsglpvsufwmnrolcwxmbvufodtdftlkuazylxwjaqcnvnathtzrxaxpbtpoqyedzwdazmwpygzmikwpbuvnqlejdumkdxzyxublcawwlykjgvhbhdlhpjiztbuvvqolwfaviwbnooekjncyaisibdldxydkdtpulquwmitdnpjgzdpmeprkbregvmgwhjmokhhftozfhhnyyqovuacmnmhclsdyhrnrdtvzfpxcpgnejjyozqkoqzacfabbfzrrokbcxzsfeqkcxkzfjjtqyiurfgtwittlzyvooxywevqqqabjzevlygqimwyrzcfvivndmdgliyovhybyklbcreqbpavyycgvxtsvcilnhrbwpjhbdpmsqyixiyzcywopztvzbabddxofmafxooumrzhfilyegilsupqljcfucgydpglurrwngwaajuopqqiknbykjtcoxkcanzxcsfkcwbmyxiebfkaojfqbyhsnhbqcsolmvoguhaomfvgtvfruajxttmheofzrdvwdifkvmgczbpsijewbfydwhibbavyrgbvruxyjxwyxxrmwnbkhmferwhbhbuurotcyupktkexnbzcskarsqgtmlhsezalnwkddgklhcaytncyfpsgrasgzxvqdyzfufqlkhcfykztcnypmangnuvkcfnfnjunnwdcxbtzvnhgrhqnduozvqbestwyxomfjuurrxvjwfusyqsgilqgihucrwiihmykpgjbwwkzwnagqngtvtufhhpcqpvypybzndnuxnuxcjuvvedglyjbiikrvhrmshsyvyilgitnccwmhenadmkrfvkyutcqrgvhszafvsgpplwndinjxsdvoygofjbuijmtojknwoukqgzqqaxaerzznpelookalbyjcjedekroepjpneruaihgxtunivhmdneljnbhhmlekeefocgfcantsfcystwhkksfoawfgauopzeskqumoavmbeawyylzgabsfsfdldzezvvdidoegzyobmdaeowshuwwxxaswczpaykvkyrwlixxkeonjjnzlrfpbgmrlvgryjqmbdvjhrrkjaqmkgpmtzxhyombreolaobjbixepcwfbzyrhjkvqvdwblxttprawmlrtikvukydhtobvatgvieoxqrbtagkbjwiuzyunjdquxmzcejeoowlupstmkqzfrguffffpizqlnighdpsaabcqtrxguhyhhzrrsdwlpgwyygbpudpxdxqgckmpvzjsnmhznhzkkceomwlrdnjjarasomfraiydswbzwvlygygbtfoawwsflhtgexbaeotfhfokiaxeerqecdwbkuoutdahpbvosbxjlpvgaubsfpgmppqmsffgfwcphthaawskscuwhbjvqthhxyfcvzkfismdeoguryafwdfstrndoqwjytbganmbpinjcnemijucqhekatenftxwycbylehctyxmwohhaqlilecxlwhjixzgstnpvqjqiedkvgcthaafzurjkxskymkateofvmewwalcmywtrlnhtpxoknmjrmajhiouxvytttuwbarlewegptlourwhpzejayoyzjygcnjhqmsgneuajfyyweditelealzjnwwdqnznmbheppnqttoechqmnrqgnhojehnwauotgkxqwswcyfmfefuycbzqutzpcxzeziwsewnihvidkyxupyjxowtpabjgincjaaxahbadxizimilhgqwbfasephsoncpyokqcxhwnqqmftgnzrpotemweubivjtlkefhefgiingkukpoqakkizzbyryqlthpwyiiwxtszdrucddvwkbvazbwwfyrsusdqzqlbbghmgogfkabaopenezmykvdabjnovrebhpanpguamvbswuebryanmaifxwwvxsqskmoqmbvrkwjgkkictbrwswnvbapgbiuxbnbwymyposzlwgklbckzzxwtxgpsxjlkfympahxftmarlqrleoinppthrdxiqasapmediekmxpwwgawbtlmgomejcxkpepeefojswvdlyoafwiwxdldnrhirgnevkgpzksxwzppmdvlwnsynwzhiizzgagjxbfmnyaxfdkwxxanxbmmbauvhbjetqkcexmyrlgyxnmjodmuwjrxaiyzwkdaaerexgqkvwbfvaoakofuikxiiilodglyzjxuhespifzeozjxwzurbyjclqiyjyowwokemiakbnjlpvgzaqqklnhbdaxldifunjtqcvubnpeetnhrdnpemjwzxuqwbfdkbjbkzbrdqjmdtqbnxsbqjmbmzctyrsbjobzfevvytuhsgwxgilffdasxhzhihfpiymqzptvssmvpayupggepznusyboilojxnotagbmewjfmjbdduwjrdnuooxkhtozramtlxaunvzczcjogzmchrivzpiieshwfeassayddsfhleqjdnloinszgbdjngjhecmgodpmowwlpvmcgsuuerzhezeyejhpnlvesiiudniyzyphmwnkhvreciyskiyletzqvjopzbfbtquixhbyoqywhncageqbiugvvnndhfoayehaofbucmonrpvvqjjmnbcgezwfvqwnyisjcikslnywworgqkumbigekrfmihsmltvmuewlocqjyyoxdslamhjermikteawoxfquuzwozoxfvhfqozzfxupxmrzirstscqefztavjluurbaziiuixttyuadbvophauiwzxodqsllkhuqofdvwpfsphziddlvlkztipauevrcrksvpuwgxltithvkswoftkxygucnbwalovfjdlsidhqiixjluxgbdzvoaaulsyvhifcedghyzbtjqjfduyqdkinmpmyovqwdexnrpzbnazyfqvjqwmcrqufmawupnxefaixunlfwzzjpyoqqugykmqojgdiwemaujcqsdyvlaglksihcsywwbbgzhpejlmvbwtxlbakvgybodjhrtfxmrwybdojqgkvzacztstuywoklmxfmdewbkasogtabtsbaytwysxeupuiuwxoogsxqasbgtybqxjosohqzfvtosxkobcosyyhipnzwmlxbgqvhgjernpyrcvuayttskiqrrwwwfzlbsjyetqmspttlpitbacrtxojzeldtudpbigrbjbjgkwukllqdupesnmuendgdylcvrrwyjfizkpilhgisbhiolojsqbmqehswaptqvdznlesveppplsnzpuegefcwhhrqnolbgkrmejhynvsbasxmpopkxpepjfiqsgpbqnyfhrfzcfyitpkmzsfmkhleligfqzjusccpbtegjewrpgkjqdyfawjabqpjsfjoytjqhqkunrvzpzxjbagggnzftvrgjyixzkkddjroukpvovlajnzlytcdmzpyxpvxkfeqolndinwonmjwacfazpvrzgtpzqcfewzsrxzoxwxsfyvwjflhnxxiqsapudhvwthxfsjrabbltmurwnfwsxnfvvdzwekotfkwabeameutbfwsgvvmqaqexgxqeavivnysdrkeyiwuyoviparneaudpyqebjmqtadavcuhzwmaoewnwhznouqpymeyozldgepyjlhnrszxwsurzgqrtyilwzjvypcrqayswtqachqbkptzlnjurnawzqyaqpamaljmtejfpyrrvmducyrdjjbbtxtbqrxssqijpctzcvxcclsrwxwpdvnolysqgnllieezdwtvvzujanextmfbykqogkcwcubcxdqyyieifzemcdqswngmopoufbrnovtccedaldabxmqaabzqltcjrttxizevnrnmtmyivzqcuqgpdutiiyqhfxxwemmyiixlomiuhqzktklxwgnuomxsvwdflhkrrmwwvzfwmnerkqgakatfyrceuzywyxfdhisixzhavjlmehyklvhjztxepogfyvyqpogwqxrfqomcfgmysohaptzipfmjtnsqfktexqqzryglcbzsirwcjcfynmlbfziadzibaisdosapioaqgzmcnhjjoyungnvzmkvpzeumfbulbtvfdnzbfscumizqcapmeaouhoprcofqjpdsnlqmkphegtltsknunzigmixnagfgmhwrghwvwegaijwnkmraffvgttlmcklszyuyohkavhgnwkjvfpbxbasxmgrgcbyxilhondfmxrpnwuijujufponedqdnfficgwttnxemcbjlrgsbmslwjjqoslwneuqymldajdzdyiztddptxaaitmpmizwiodajusscojvgryvpfwuvsvsnotfnvwqzwroamjnaqmyacodoedsjbjdtdregvzxyovybajyxccjzafjeyiqsaxiflusiyhthbkrvzpamckjglwtlzusbfvtbxxcxdxpwwavcgmipwfecupzhphgsjabgkzwsbabdcqtphwqkxpsfwwuklsfdiiznpdcohcclmnamcovugpzbrehxfotjbdxxvpyfkcnpnwvbwkqeldcbkjrxyvvkqoxraqwaftgutgfshsdsywvsicbrwyjhfbzkhxvusewmkqjrtdlnvmtmkwxorfbreskduvclpqhhisjfuviilgdxwldnxwxdfmibczmyptjhawgpzquvpiemjlbpnpocwquecqkxxzwaxgmvwcmjmdacnauohqtajknuprxzuwbgzrvisbjjadnyxawzxyfqtmndhhfuwnlpsyeqwuzghpcmbicixfbmwrfrcugdplnekjzmjxnashqhskyeaoudaqjhgkwortfhzqarkqdbitmqqgwdukdgjzejtodegpredlwkeyygcftzawdrsowrzjlscbxxqkiryxyruvswmxngfkctjglfdxrqieoiajoiakmyqxoguwuffsbtngguekneeglbvhiqpiflyqkroxhdfikwhmotchizajztfihrzmmvnwqdjvspsmbetswubaafvhvhkkusmyskktcsnycodmjmvdtgpprnvhknbollwhrqueqlavuljmkphapixzkhoflhohwmdmhaubtwholnzpefgkgzzhkzdnntrufoyzwepfxmtfnpbewyoiacedmwedgyjdkxywxwbokcfpxywrkvbcqwwiqfyiygqqaoxtssszyjnbywofkwknlkmsmylofgjemslpwmcbrsjuxxehwfevkijufgbjszicufklgvcaxvzvzkxqxtowgrgofbwkwfygefwxcyxlgjgdcmxpvihgvwxoqwpmjkrewauygrnfgoeqrgpwvtbkpmbsxexqoecfeatubblgnmrtzzvsibviojfjftxiqxhxmjisxlgslhudjbrbrjrsdwrdxwmdltiymdmmgcklpmgfjntehaefmvukfpfsbbjsdtcpcppxkwoqgempyyobqaqdkohepjcasbfornxehvzfnochfmmjegwdesoyexqqzirutddokoofrhqwqfeovjikuhskzciwmbdfurdxkakccejvnizgmxaxffvxvzfanuzaiaayeqrcqfahqowhzrhxnivygwbozthyvofuutqnjukgmsxftjflcyqptiryxuzhrwnqfxqblunmikfylhpwofmfjeinpbkeojxypfdbrracaiuiwbwamkvozgguczzwxmbvozziwygpccemzqtgzzeqwblnitbrinqrbjholgwcivaevkzavnvkuxqemffjbwcsxeivpqqrfwrfshelxmyujyvguymqoavskmpegacbqhhpljsaapaeuiljkgbtlnlebubtinfynlhgvadmxhbzbjifsbtbitzinrgajbrhpecponumabqgecekdxydqufzfzbzoutrbypenuufgmajvndygalnnrggftlnuthaecczemghagdopomednlzgpryclyruelvpmvndgzaavopkixsgkrnlmnvstekraeretfdstjzcnwvfrhzmwmaizndibcuxnpxivsipyjnwxirxtodlzdknaughjltgpcszuxitcmamhzgklcclxrwichaiwpsrcntsdqfqwxdypvxpzjhfkktvtvwsqokoztkrdebtbbsbsrmaaigscicsdjktakejgdigqljuxaqhwazkgiqtwlcalpuhjpavpvwohkxnjknlwngkgetadoquzgjkllekmmbzkm";
        System.out.println(SlidingWindow.longestDupSubstringOptimized(s));

    }
}
