    // 파라미터(username, 입력값, 패턴, 에러메시지, 에러메시지 출력할 span)
    function check(name, value, pattern, msg, target) {
      target.text('');
      if(value==='') {
        target.text(name + ": 필수입력입니다");
        return false;
      } else if(pattern.test(value)===false) {
        target.text(msg);
        return false;
      }
      return true;
    }

    function usernameCheck() {
      const pattern = /^[a-z0-9]{6,10}$/;
      const username = $('#username').val();
      const $usernameMsg = $('#username-msg');
      return check('아이디', username, pattern, '아이디는 소문자와 숫자 6~10자입니다', $usernameMsg);
    }

    function passwordCheck() {
      const pattern = /^[A-Za-z0-9]{6,10}$/;
      const password = $('#password').val();
      const $passwordMsg = $('#password-msg');
      return check('비밀번호', password, pattern, '비밀번호는 영숫자 6~10자입니다', $passwordMsg);
    }

    function emailCheck() {
      const pattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      const email = $('#email').val();
      const $emailMsg = $('#email-msg');
      return check('이메일', email, pattern, '정확한 이메일을 입력하세요', $emailMsg);
    }

    function password2Check() {
      const password = $('#password').val();
      const password2 = $('#password2').val();
      const $password2Msg = $('#password2-msg');
      $password2Msg.text('');
      if(password2==='') {
        $password2Msg.text('새 비밀번호 : 필수입력입니다');
        return false;
      } else if(password!==password2) {
        $password2Msg.text('새 비밀번호가 일치하지 않습니다');
        return false;
      }
      return true;
    }